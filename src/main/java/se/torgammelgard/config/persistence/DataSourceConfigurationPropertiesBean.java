package se.torgammelgard.config.persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:persistence-hibernate.properties")
public class DataSourceConfigurationPropertiesBean {

    @Value("${jdbc.url}")
    private String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
