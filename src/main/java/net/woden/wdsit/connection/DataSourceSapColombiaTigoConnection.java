/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class DataSourceSapColombiaTigoConnection {
    
    @Autowired
    private Environment en;
    @Autowired
    private EncryptUtility eu;

    @Primary
    @Bean
    public DataSource sourceSapColombiaTigo() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.en.getProperty("spring.datasource.driver-class-name.SapColombiaTigo"));
        dataSource.setUrl(this.en.getProperty("spring.datasource.url.SapColombiaTigo"));
        dataSource.setUsername(this.en.getProperty("spring.datasource.username.SapColombiaTigo"));
        dataSource.setPassword(this.eu.decode(this.en.getProperty("spring.datasource.password.SapColombiaTigo")));

        return dataSource;
    }

    public Connection openConnection() {
        try {
            return sourceSapColombiaTigo().getConnection();
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
