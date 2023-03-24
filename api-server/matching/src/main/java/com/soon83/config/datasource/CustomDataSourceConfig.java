package com.soon83.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
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
@EnableConfigurationProperties({DataSourceMasterProperties.class, DataSourceSlaveProperties.class})
public class CustomDataSourceConfig extends HikariConfig {
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
//        EntityManagerFactoryBuilder entityManagerFactoryBuilder = new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), jpaProperties.getProperties(), null);
//        return entityManagerFactoryBuilder
//                .dataSource(dataSource(routingDataSource()))
//                .packages("com.soon83")
//                .properties(properties)
//                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory().getObject()));
    }

    @Bean
    public LazyConnectionDataSourceProxy dataSource(DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
}