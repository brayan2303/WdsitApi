/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.model.XmlSerialStatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author b.algecira
 */
@Service
//@WebService
public class XmlSerialStatusService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private Environment en;
 

    public @ResponseBody
    XmlSerialStatusModel xmlSerialEntry(String serial) {
        XmlSerialStatusModel x = null;
        Connection cn = this.ds.openConnection();

        String[][]s = {{"asd","asdasd"},{"asdasd","asdasda"},{"Hola","Hola"}};
        try {
            CallableStatement cs = cn.prepareCall("{call sp_XmlSearchSerialEntry(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                x = new XmlSerialStatusModel();
                x.setId(rs.getInt("id"));
                x.setSerial(rs.getString("serial"));
                x.setEstado(rs.getString("estado"));
                x.setCustomer(rs.getString("customer"));
                x.setXml(s);
                 
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
            }
        }
        return x;
    }
    public @ResponseBody
    XmlSerialStatusModel xmlSerialProcces(String serial) {
        XmlSerialStatusModel x = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_XmlSearchSerialProcess(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                x = new XmlSerialStatusModel();
                x.setId(rs.getInt("id"));
                x.setSerial(rs.getString("serial"));
                x.setEstado(rs.getString("estado"));
                x.setCustomer(rs.getString("customer"));
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            System.out.println("Error");
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {

            }
        }
        return x;
    }

    public @ResponseBody
    XmlSerialStatusModel xmlSerialDispatch(String serial) {
        XmlSerialStatusModel x = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_XmlSearchSerialDispatch(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                x = new XmlSerialStatusModel();
                x.setId(rs.getInt("id"));
                x.setSerial(rs.getString("serial"));
                x.setEstado(rs.getString("estado"));
                x.setCustomer(rs.getString("customer"));
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            System.out.println("Error");
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {

            }
        }
        return x;
    }

    public @ResponseBody
    XmlSerialStatusModel xmlPrueba(String serial) {
        XmlSerialStatusModel x = null;
        XmlSerialStatusModel y = null;
        XmlSerialStatusModel z = null;
        Connection cn = this.ds.openConnection();
        Connection cna = this.ds.openConnection();
        Connection cns = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_XmlSearchSerialEntry(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                x = new XmlSerialStatusModel();
                x.setId(rs.getInt("id"));
                x.setSerial(rs.getString("serial"));
                x.setEstado(rs.getString("estado"));
                x.setCustomer(rs.getString("customer"));
            }
            CallableStatement Call = cna.prepareCall("{call sp_XmlSearchSerialProcess(?)}");
            Call.setString(1, serial);
            ResultSet rsl = Call.executeQuery();
            while (rsl.next()) {
                y = new XmlSerialStatusModel();
                y.setId(rsl.getInt("id"));
                y.setSerial(rsl.getString("serial"));
                y.setEstado(rsl.getString("estado"));
                y.setCustomer(rsl.getString("customer"));
            }

            CallableStatement csa = cns.prepareCall("{call sp_XmlSearchSerialDispatch(?)}");
            csa.setString(1, serial);
            ResultSet rss = csa.executeQuery();
            while (rss.next()) {
                z = new XmlSerialStatusModel();
                z.setId(rss.getInt("id"));
                z.setSerial(rss.getString("serial"));
                z.setEstado(rss.getString("estado"));
                z.setCustomer(rss.getString("customer"));
            }
            cs.close();
            Call.close();
            csa.close();
            rs.close();
            rsl.close();
            cn.close();
            cna.close();
            cns.close();

        } catch (SQLException ex) {
            System.out.println("Error");
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return x;
    }

    public @ResponseBody
    XmlSerialStatusModel xmlPrueba2(String serial) {
        XmlSerialStatusModel x = null;
        XmlSerialStatusModel y = null;
        XmlSerialStatusModel z = null;
        Connection cn = this.ds.openConnection();
        Connection cna = this.ds.openConnection();
        Connection cns = this.ds.openConnection();
        if (x != null) {
            try {
                CallableStatement cs = cn.prepareCall("{call sp_XmlSearchSerialEntry(?)}");
                cs.setString(1, serial);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    x = new XmlSerialStatusModel();
                    x.setId(rs.getInt("id"));
                    x.setSerial(rs.getString("serial"));
                    x.setEstado(rs.getString("estado"));
                    x.setCustomer(rs.getString("customer"));
                }
            } catch (SQLException ex) {
                System.out.println("Error");
            }
            if (y != null) {
                try {
                    CallableStatement Call = cna.prepareCall("{call sp_XmlSearchSerialProcess(?)}");
                    Call.setString(1, serial);
                    ResultSet rsl = Call.executeQuery();
                    while (rsl.next()) {
                        y = new XmlSerialStatusModel();
                        y.setId(rsl.getInt("id"));
                        y.setSerial(rsl.getString("serial"));
                        y.setEstado(rsl.getString("estado"));
                        y.setCustomer(rsl.getString("customer"));
                    }

                } catch (SQLException ex) {
                    System.out.println("Error");
                }
                if (z != null) {
                    try {
                        CallableStatement csa = cns.prepareCall("{call sp_XmlSearchSerialDispatch(?)}");
                        csa.setString(1, serial);
                        ResultSet rss = csa.executeQuery();
                        while (rss.next()) {
                            z = new XmlSerialStatusModel();
                            z.setId(rss.getInt("id"));
                            z.setSerial(rss.getString("serial"));
                            z.setEstado(rss.getString("estado"));
                            z.setCustomer(rss.getString("customer"));
                        }
                    } catch (SQLException ex) {
                        System.out.println("Error");
                    }

                } else {
                    System.err.println("error");
                }

            } else {
                System.err.println("erro");
            }
        } else {
            System.err.println("error");
        }
        return x;
    }

    private Object toString(String[][] s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
