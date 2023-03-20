package com.soon83.config;

import com.soon83.ApiServerMatchingApplication;
import com.soon83.config.datasource.DataSourceMasterProperties;
import com.soon83.config.datasource.DataSourceReplicationRouting;
import com.soon83.config.datasource.DataSourceSlaveProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Profile("test")
@Configuration
@RequiredArgsConstructor
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigurationProperties({DataSourceMasterProperties.class, DataSourceSlaveProperties.class})
public class CustomDataSourceConfig {
    private final DataSourceMasterProperties masterDatabaseProperty;
    private final DataSourceSlaveProperties slaveDatabaseProperty;
    private final JpaProperties jpaProperties;

    public DataSource createDataSource(String url) {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .url(url)
                .driverClassName(masterDatabaseProperty.getDriverClassName())
                .username(masterDatabaseProperty.getUsername())
                .password(masterDatabaseProperty.getPassword())
                .build();
    }

    @Bean
    public DataSource routingDataSource() {
        DataSourceReplicationRouting dataSourceReplicationRouting = new DataSourceReplicationRouting();
        Map<Object, Object> dataSourceMap = new LinkedHashMap<>();
        DataSource master = createDataSource(masterDatabaseProperty.getUrl());
        dataSourceMap.put("master", master);
        slaveDatabaseProperty.getSlaveList().forEach(slave -> dataSourceMap.put(slave.getName(), createDataSource(slave.getUrl())));
        dataSourceReplicationRouting.setDefaultTargetDataSource(master);
        dataSourceReplicationRouting.setTargetDataSources(dataSourceMap);
        return dataSourceReplicationRouting;
    }

    @Bean
    public DataSource customDataSource() {
        return new LazyConnectionDataSourceProxy(routingDataSource());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        AbstractJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        EntityManagerFactoryBuilder entityManagerFactoryBuilder = new EntityManagerFactoryBuilder(vendorAdapter, jpaProperties.getProperties(), null);
        return entityManagerFactoryBuilder
                .dataSource(customDataSource())
                .packages("com.soon83")
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory().getObject()));
    }
}