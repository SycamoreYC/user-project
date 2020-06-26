package com.java.userproject.configs;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricConfiguration {

    @Bean(name = "userCounter")
    public Counter userCounter(MeterRegistry registry) {
        return Counter
                .builder("api.users.counter")
                .description("Amount of user api called")
                .register(registry);
    }

    @Bean(name = "emailCounter")
    public Counter emailCounter(MeterRegistry registry) {
        return Counter
                .builder("api.email.counter")
                .description("Amount of email api called")
                .register(registry);
    }

}
