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
public class DataSourceSapEcuadorDirectvConnection {
    
    @Autowired
    private Environment en;
    @Autowired
    private EncryptUtility eu;

    @Primary
    @Bean
    public DataSource sourceSapEcuadorDirectv() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.en.getProperty("spring.datasource.driver-class-name.SapEcuadorDirectv"));
        dataSource.setUrl(this.en.getProperty("spring.datasource.url.SapEcuadorDirectv"));
        dataSource.setUsername(this.en.getProperty("spring.datasource.username.SapEcuadorDirectv"));
        dataSource.setPassword(this.eu.decode(this.en.getProperty("spring.datasource.password.SapEcuadorDirectv")));

        return dataSource;
    }
    public Connection openConnection(){
        try {
            return sourceSapEcuadorDirectv().getConnection();
        } catch (SQLException ex) {
            return null;
        }
    }
    
    
    
}
