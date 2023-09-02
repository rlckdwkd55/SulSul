package com.sulsulmarket.sulsul.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.hikari")
@Getter @Setter
@Slf4j
public class DataSourceConfig {

    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;
    private int maximumPoolSize;
    private int minimumIdle;

    /**\
     * DataSource Setting
     */
    @Bean
    public DataSource dataSource() {
        log.debug("jdbc : {}, username : {}, password : {} , driverClassName : {} , maximumPoolSize : {}, minimumIdle : {}", jdbcUrl,username,password,driverClassName,maximumPoolSize,minimumIdle);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setMaximumPoolSize(maximumPoolSize);
        dataSource.setMinimumIdle(minimumIdle);

        return dataSource;
    }
}
