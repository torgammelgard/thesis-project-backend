package se.torgammelgard.config.persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jpa.properties")
public class JpaConfigurationPropertiesBean {

    @Value("${jpa.showsql}")
    private boolean showSql;

    @Value("${jpa.dialect}")
    private String dialect;

    public JpaConfigurationPropertiesBean() {
    }

    public boolean isShowSql() {
        return showSql;
    }

    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }
}
