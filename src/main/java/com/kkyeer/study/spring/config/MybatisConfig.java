package com.kkyeer.study.spring.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: kkyeer
 * @Description: MybatisConfig
 * @Date:Created in 17:15 2023/5/27
 * @Modified By:
 */

@Configuration
@MapperScan(basePackages = "com.kkyeer.study.spring.dal.ck",sqlSessionTemplateRef = "ckSqlSessionTemplate")
@MapperScan(basePackages = "com.kkyeer.study.spring.dal.mysql",sqlSessionTemplateRef = "mysqlSqlSessionTemplate")
public class MybatisConfig {

    @Bean
    public SqlSessionTemplate ckSqlSessionTemplate(ClickHouseDataSource clickHouseDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(clickHouseDataSource);
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    @Bean
    public SqlSessionTemplate mysqlSqlSessionTemplate(MySQLDataSource clickHouseDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(clickHouseDataSource);
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
