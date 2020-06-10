package org.openclassroom.projet.technical.spring.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Application configuration class.
 * */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    // ==================== Attributes ====================
    @Autowired
    private Environment env;



    // ==================== Beans ====================
    /**
     * Set up the {@link DataSource datasource} to access the database.
     *
     * @return The configured {@link DataSource datasource}.
     * */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }



    /**
     * Set up the {@link javax.persistence.EntityManager} with the {@link LocalContainerEntityManagerFactoryBean}.
     *
     * @return The configured {@link EntityManagerFactory}
     * */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("org.openclassroom.projet.model");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(additionalProperties());

        return factory;
    }



    /**
     * Configure the {@link PlatformTransactionManager} with the {@link JpaTransactionManager}
     *
     * @param entityManagerFactory - The previously configured {@link EntityManagerFactory} object.
     *
     * @return The new configured {@link PlatformTransactionManager}.
     * */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }



    /**
     * To enable exception translation from Spring Data JPA
     *
     * @return An instance of {@link PersistenceExceptionTranslationPostProcessor}
     * */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }



    /**
     * Configure the implementation of the password encoder
     *
     * @return An instance of {@link BCryptPasswordEncoder}
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    // ==================== Methods ====================
    /**
     * Additional properties for configuring the database connection.
     *
     * @return {@link Properties} that contains custom properties
     * */
    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL92Dialect");

        return properties;
    }
}
