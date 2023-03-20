package com.soon83.config.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource.slave")
public class DataSourceSlaveProperties {
    private List<Slave> slaveList = new ArrayList<>();

    @Getter
    @Setter
    public static class Slave {
        private String url;
        private String name;
    }
}
