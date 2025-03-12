package example.mvcdemo.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.datasource")
@PropertySource({ "classpath:persistence-${envTarget:h2}.properties" })
public class DataSourceConfig {

    private String url;
    private String username;
    private String password;
    private String driverClassName;

    @Bean
    DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setAutoCommit(false);
        hikariConfig.setTransactionIsolation("TRANSACTION_READ_COMMITTED");
        return new HikariDataSource(hikariConfig);
    }
}
