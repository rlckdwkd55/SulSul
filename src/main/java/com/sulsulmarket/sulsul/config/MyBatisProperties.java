package com.sulsulmarket.sulsul.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@ConfigurationProperties(prefix = "mybatis")
@Getter @Setter
public class MyBatisProperties {

    private Resource configLocation;
    private Resource mappersLocation;
}
