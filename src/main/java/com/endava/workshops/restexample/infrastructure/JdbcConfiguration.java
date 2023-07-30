package com.endava.workshops.restexample.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcRepositories("com.endava.workshops.restexample.application.adapter.secondary.jdbc")
public class JdbcConfiguration {
    // ...
}
