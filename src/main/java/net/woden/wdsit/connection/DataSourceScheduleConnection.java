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
public class DataSourceScheduleConnection {

    @Autowired
    private Environment en;
    @Autowired
    private EncryptUtility eu;

    @Primary
    @Bean
    public DataSource sourceSchedule() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.en.getProperty("spring.datasource.driver-class-name.Schedule"));
        dataSource.setUrl(this.en.getProperty("spring.datasource.url.Schedule"));
        dataSource.setUsername(this.en.getProperty("spring.datasource.username.Schedule"));
        dataSource.setPassword(this.eu.decode(this.en.getProperty("spring.datasource.password.Schedule")));

        return dataSource;
    }
    public Connection openConnection(){
        try {
            return sourceSchedule().getConnection();
        } catch (SQLException ex) {
            return null;
        }
    }
}
