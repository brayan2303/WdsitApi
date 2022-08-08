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

@Configuration
public class DataSourceSapEcuadorConnection {

    @Autowired
    private Environment en;
    @Autowired
    private EncryptUtility eu;

    @Primary
    @Bean
    public DataSource sourceSapEcuador() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.en.getProperty("spring.datasource.driver-class-name.SapEcuador"));
        dataSource.setUrl(this.en.getProperty("spring.datasource.url.SapEcuador"));
        dataSource.setUsername(this.en.getProperty("spring.datasource.username.SapEcuador"));
        dataSource.setPassword(this.eu.decode(this.en.getProperty("spring.datasource.password.SapEcuador")));

        return dataSource;
    }
    public Connection openConnection(){
        try {
            return sourceSapEcuador().getConnection();
        } catch (SQLException ex) {
            return null;
        }
    }
}
