package se.torgammelgard.config.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "se.torgammelgard.repository")
@EnableTransactionManagement
public class JpaConfig {

    @Autowired
    Environment env;

    @Autowired
    private DataSourceConfigurationPropertiesBean dataSourceProperties;

    @Autowired
    private JpaConfigurationPropertiesBean jpaConfigurationProperties;

    public JpaConfig() {
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(localDataSource());
        lef.setPackagesToScan("se.torgammelgard.persistence.entities");
        lef.setJpaVendorAdapter(jpaVendorAdapter());
        lef.setJpaDialect(jpaDialect());
        lef.setMappingResources();
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", jpaConfigurationProperties.isShowSql());
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "create");
        //properties.put("hibernate.implicit_naming_strategy", env.getProperty(""));
        lef.setJpaProperties(properties);
        lef.afterPropertiesSet();

        return lef;
    }

    private JpaDialect jpaDialect() {
        return new HibernateJpaDialect();
    }

    //@Profile({"dev"})
    @Bean(name = "localDataSource")
    public DataSource localDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager() {{
            setEntityManagerFactory(entityManagerFactory().getObject());
        }};
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(jpaConfigurationProperties.isShowSql());
        hibernateJpaVendorAdapter.setDatabasePlatform(jpaConfigurationProperties.getDialect());
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        return hibernateJpaVendorAdapter;
    }
}
