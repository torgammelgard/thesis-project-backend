package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import se.torgammelgard.config.CoreConfig;

/**
 * A configuration file for test
 */
@Configuration
@Import({CoreConfig.class})
public class TestConfig {

}
