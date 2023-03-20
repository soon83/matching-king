package com.soon83.config.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource.master")
public class DataSourceMasterProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
