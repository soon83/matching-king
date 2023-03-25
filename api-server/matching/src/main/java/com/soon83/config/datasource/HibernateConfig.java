package com.soon83.config.datasource;

import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class HibernateConfig {

    @Bean
    public HibernateProperties hibernateProperties() {
        return new HibernateProperties();
    }
}
