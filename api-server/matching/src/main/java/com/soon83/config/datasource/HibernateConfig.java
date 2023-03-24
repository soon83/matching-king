package com.soon83.config.datasource;

import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    @Bean
    public HibernateProperties hibernateProperties() {
        return new HibernateProperties();
    }
}
