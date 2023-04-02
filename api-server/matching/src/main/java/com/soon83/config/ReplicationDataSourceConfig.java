package com.soon83.config;

import com.soon83.config.datasource.DataSourceReplicationRouting;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.*;

@Profile("test")
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({
        ReplicationDataSourceConfig.DataSourceMasterProperties.class,
        ReplicationDataSourceConfig.DataSourceSlaveProperties.class
})
public class ReplicationDataSourceConfig extends HikariConfig {
    private final DataSourceMasterProperties masterDatabase;
    private final DataSourceSlaveProperties slaveDatabase;
    private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    public DataSource createDataSource(String url) {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .url(url)
                .driverClassName(masterDatabase.getDriverClassName())
                .username(masterDatabase.getUsername())
                .password(masterDatabase.getPassword())
                .build();
    }

    @Bean
    public DataSource routingDataSource() {
        DataSource master = createDataSource(masterDatabase.getUrl());
        Map<Object, Object> dataSourceMap = new LinkedHashMap<>();
        dataSourceMap.put("master", master);
        slaveDatabase.getSlaves().forEach(slave -> dataSourceMap.put(slave.getName(), createDataSource(slave.getUrl())));

        DataSourceReplicationRouting dataSourceReplicationRouting = new DataSourceReplicationRouting();
        dataSourceReplicationRouting.setDefaultTargetDataSource(master);
        dataSourceReplicationRouting.setTargetDataSources(dataSourceMap);
        return dataSourceReplicationRouting;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource(routingDataSource()));
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.soon83");
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(properties);
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory().getObject()));
    }

    @Bean
    public LazyConnectionDataSourceProxy dataSource(DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Getter
    @Setter
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public static class DataSourceMasterProperties {
        private String driverClassName;
        private String url;
        private String username;
        private String password;
    }

    @Getter
    @Setter
    @ConfigurationProperties(prefix = "spring.datasource")
    public static class DataSourceSlaveProperties {
        private List<Slave> slaves = new ArrayList<>();

        @Getter
        @Setter
        public static class Slave {
            private String url;
            private String name;
        }
    }
}
