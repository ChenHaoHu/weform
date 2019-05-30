package com.hcy.core.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
//basePackages 配置的是mapper接口对应的包
@Configuration
@MapperScan(basePackages = "com.hcy.core.mapper_read", sqlSessionTemplateRef  = "ReadSqlSessionTemplate")
public class DataSourceReadConfig {
 
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration globalConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }
 
    @Bean(name = "ReadDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysqlread")
    @Primary
    public DataSource readDataSource() {
        return DataSourceBuilder.create().build();
    }
 
    @Bean(name = "ReadSqlSessionFactory")
    @Primary
    public SqlSessionFactory readSqlSessionFactory(@Qualifier("ReadDataSource") DataSource dataSource, org.apache.ibatis.session.Configuration config) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(config);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper_read/*.xml "));
        return bean.getObject();
    }
 
    @Bean(name = "ReadTransactionManager")
    @Primary
    public DataSourceTransactionManager readTransactionManager(@Qualifier("ReadDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
 
    @Bean(name = "ReadSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate readSqlSessionTemplate(@Qualifier("ReadSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
 
}
