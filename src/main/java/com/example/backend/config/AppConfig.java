package com.example.backend.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:/application.properties"})
@MapperScan(basePackages = {
        "com.example.backend.user.mapper",
        "com.example.backend.account.mapper",
        "com.example.backend.roombasic.mapper",
        "com.example.backend.transaction.mapper"
})
@ComponentScan(basePackages = {
        "com.example.backend.user.service",
        "com.example.backend.account.service",
        "com.example.backend.roombasic.service",
        "com.example.backend.transaction.service"
})
public class AppConfig {
    public AppConfig() {
        System.out.println("AppConfig created");
    }

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        config.setJdbcUrl(dbUrl);
        config.setUsername(username);
        config.setPassword(password);

        config.setConnectionTimeout(30000);
        config.setMinimumIdle(3);
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        config.setAutoCommit(true);

        return new HikariDataSource(config);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));

        Resource[] mapperLocations = new Resource[] {
                new ClassPathResource("mapper/UserMapper.xml"),
                new ClassPathResource("mapper/AccountMapper.xml"),
                new ClassPathResource("mapper/RoomBasicMapper.xml"),
                new ClassPathResource("mapper/TransactionMapper.xml"),
        };
        sessionFactory.setMapperLocations(mapperLocations);

        return sessionFactory.getObject();
    }
    private org.apache.ibatis.session.Configuration getConfiguration() {
        org.apache.ibatis.session.Configuration configuration =
                new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}