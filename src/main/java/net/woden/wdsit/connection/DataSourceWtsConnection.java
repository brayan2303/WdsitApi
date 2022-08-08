/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.connection;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author b.algecira
 */
@Configuration
public class DataSourceWtsConnection {

    @Autowired
    private Environment en;
    @Autowired
    private EncryptUtility eu;

    @Primary
    @Bean
    public DataSource sourceWtsConnection() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.en.getProperty("spring.datasource.driver-class-name.wts"));
        dataSource.setUrl(this.en.getProperty("spring.datasource.url.wts"));
        dataSource.setUsername(this.en.getProperty("spring.datasource.username.wts"));
        dataSource.setPassword(this.eu.decode(this.en.getProperty("spring.datasource.password.wts")));

        return dataSource;
    }

    public Connection openConnection() {
        try {
            return sourceWtsConnection().getConnection();
        } catch (SQLException ex) {
            return null;
        }
    }
}
