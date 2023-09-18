package com.sulsulmarket.sulsul.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="database")
@Configuration
@Getter@Setter
public class DataBaseConfig {

    private String mainTableName;
    private String loginTableName;
}
