package se.torgammelgard.config.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
//@ComponentScan("se.torgammelgard.persistence.entities")
@ComponentScan("se.torgammelgard.service")
@PropertySource("classpath:persistence-hibernate.properties")
public class PersistenceConfig {

    @Autowired
    Environment env;

    public PersistenceConfig() {
    }

    @Bean(name = "devDataSource")
    public DataSource dataSource() {
        return new DriverManagerDataSource(){{
            setDriverClassName(env.getProperty("jdbc.driver.classname"));
            setUrl(env.getProperty("jdbc.url"));
            setUsername(env.getProperty("jdbc.username"));
            setPassword(env.getProperty("jdbc.password"));
        }};
    }

    @SuppressWarnings("serial")
	Properties hibernateProperties() {
        return new Properties() {{
            //setProperty("hibernate.connection.driver_class", env.getProperty("jdbc.driverClassname"));

        }};
    }
}
