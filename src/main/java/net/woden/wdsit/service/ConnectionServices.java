/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ConnectioGeneralEntity;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ConnectionServices {

    @Autowired
    private DataSourceConnection ds;
    
    @Autowired
    private EncryptUtility eu;

    public DataSource sourceGeneralProof(int customerId, int countryId) throws SQLException {
        ConnectioGeneralEntity c = null;
        Connection cn = this.ds.openConnection();
        String driver = "";
        String url = "";
        String name = "";
        String password = "";
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ConnectioGeneralFindByCustomerId(?,?)}");
            cs.setInt(1, customerId);
            cs.setInt(2, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                c = new ConnectioGeneralEntity();
                c.setDriver(rs.getString("driver"));
                c.setUrl(rs.getString("url"));
                c.setName(rs.getString("name"));
                c.setPassword(rs.getString("password"));

                driver = c.getDriver();
                url = c.getUrl();
                name = c.getName();
                password = c.getPassword();
            }
            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            cn.close();
        }
        
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.eu.decode(driver));
        dataSource.setUrl(this.eu.decode(url));
        dataSource.setUsername(this.eu.decode(name));
        dataSource.setPassword(this.eu.decode(password));
        cn.close();
        return dataSource;
    }

    public Connection openConnection(int customerId, int countryId) throws SQLException {
        try {
            return sourceGeneralProof(customerId, countryId).getConnection();
        } catch (SQLException ex) {
            return null;
        }
    }
}
