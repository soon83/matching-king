package com.soon83.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;

@Slf4j
public class DataSourceReplicationRouting extends AbstractRoutingDataSource {
    private DataSourceRoundRobinList<String> dataSourceNameList;

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        dataSourceNameList = new DataSourceRoundRobinList<>(
                targetDataSources.keySet()
                        .stream()
                        .map(Object::toString)
                        .filter(string -> string.contains("slave"))
                        .toList()
        );
    }

    @Override
    protected Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        if (isReadOnly) {
            String slave = dataSourceNameList.getOne();
            log.info("# Connection Slave: {}", slave);
            return slave;
        } else {
            log.info("# Connection Master");
            return "master";
        }
    }

    public static class DataSourceRoundRobinList<T> {
        private final List<T> list;
        private Integer counter = 0;

        public DataSourceRoundRobinList(List<T> list) {
            this.list = list;
        }

        public T getOne() {
            if (counter + 1 >= list.size()) {
                counter = -1;
            }
            return list.get(++counter);
        }
    }
}
