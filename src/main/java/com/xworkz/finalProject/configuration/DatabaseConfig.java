package com.xworkz.finalProject.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:Application.properties")
@Slf4j
public class DatabaseConfig {
    @Value("${Jdbc.driver}")
    private String driver;
    @Value("${Jdbc.url}")
    private String url;
    @Value("${Jdbc.userName}")
    private String userName;
    @Value("${Jdbc.password}")
    private String password;

    public DatabaseConfig(){
        log.info("Created no-arg constructor in DatabaseConfig...");
    }
    @Bean
    public DataSource dataSource(){
        System.out.println("Running Datasource in DatabaseConfig.....");
        DriverManagerDataSource driverMSource =new DriverManagerDataSource();
        driverMSource.setDriverClassName(driver);
        driverMSource.setUrl(url);
        driverMSource.setUsername(userName);
        driverMSource.setPassword(password);
        return driverMSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(DataSource dataSource){
        System.out.println("Running LocalContainerEntityManagerFactoryBean in DatabaseConfig...");
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean=new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.xworkz.finalProject");
        JpaVendorAdapter jpaVendorAdapter=new HibernateJpaVendorAdapter();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        return localContainerEntityManagerFactoryBean;

    }

}
