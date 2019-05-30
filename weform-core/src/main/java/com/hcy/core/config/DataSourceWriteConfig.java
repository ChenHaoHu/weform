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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
 
/**
 * Created by summer on 2016/11/25.
 */
@Configuration
@MapperScan(basePackages = "com.hcy.core.mapper", sqlSessionTemplateRef  = "WriteSqlSessionTemplate")
public class DataSourceWriteConfig {
 
    @Bean(name = "WriteDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysqlwrite")
    public DataSource WriteDataSource() {
        return DataSourceBuilder.create().build();
    }
 
    @Bean(name = "WriteSqlSessionFactory")
    public SqlSessionFactory writeSqlSessionFactory(@Qualifier("WriteDataSource") DataSource dataSource, org.apache.ibatis.session.Configuration config) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(config);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session
                .Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*.xml"));
        return bean.getObject();
    }
 
    @Bean(name = "WriteTransactionManager")
    public DataSourceTransactionManager writeTransactionManager(@Qualifier("WriteDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
 
    @Bean(name = "WriteSqlSessionTemplate")
    public SqlSessionTemplate writeSqlSessionTemplate(@Qualifier("WriteSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
 
}
