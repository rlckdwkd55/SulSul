package com.sulsulmarket.sulsul.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.sulsulmarket.sulsul.*.dao")
@ConfigurationPropertiesScan("com.sulsulmarket.sulsul.config")
@Slf4j
public class MyBatisConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyBatisProperties myBatisProperties;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);


        sessionFactoryBean.setConfigLocation(myBatisProperties.getConfigLocation());
        sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/mapper/*.xml"));
//        sessionFactoryBean.setMapperLocations(new Resource[]{myBatisProperties.getMappersLocation()});
        log.info("설정 읽어오는 곳이 어데고 : {}",String.valueOf(myBatisProperties.getConfigLocation()));
        log.info("설정 읽어오는 곳이 어데고 : {}",String.valueOf(applicationContext.getResources("classpath:/mappers/mapper/*.xml")));

        return sessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {

        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
