package se.torgammelgard.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;

import se.torgammelgard.config.web.WebConfig;

/**
 * Configuration file - Entry point
 */
@Configuration
@EnableAsync
@ComponentScan(basePackages = {"se.torgammelgard"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebConfig.class)})
public class CoreConfig {

}
