package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.LoadValidateReportEntity;
import net.woden.wdsit.model.ReportValidateLoadModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author f.casallas
 */
@Service
public class ReportValidateService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(LoadValidateReportEntity l) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadValidateReportCreate(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, l.getIdCountry());
            cs.setString(2, l.getAvailableDate());
            cs.setString(3, l.getModelCode());
            cs.setString(4, l.getModelDescription());
            cs.setString(5, l.getDxType());
            cs.setString(6, l.getSerialEquipo());
            cs.setString(7, l.getAddress());
            cs.setString(8, l.getRegion());
            cs.setString(9, l.getCity());
            cs.setString(10, l.getDepartment());
            cs.setInt(11, l.getUserId());
            cs.registerOutParameter(12, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(12);
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }

    public ResponseModel update(LoadValidateReportEntity l) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadValidateReportUpdate(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, l.getIdCountry());
            cs.setString(2, l.getAvailableDate());
            cs.setString(3, l.getModelCode());
            cs.setString(4, l.getModelDescription());
            cs.setString(5, l.getDxType());
            cs.setString(6, l.getSerialEquipo());
            cs.setString(7, l.getAddress());
            cs.setString(8, l.getRegion());
            cs.setString(9, l.getCity());
            cs.setString(10, l.getDepartment());
            cs.setInt(11, l.getUserId());
            cs.registerOutParameter(12, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(12);
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel ListCountry(int userId) {
        ArrayList<ReportValidateLoadModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_SearchCountryActive(?)}");
            cs.setInt(1, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ReportValidateLoadModel d = new ReportValidateLoadModel();
                d.setIdCountry(rs.getInt("idCountry"));
                d.setCountry(rs.getString("Country"));
                d.setActive(rs.getBoolean("active"));
                list.add(d);

            }
            cn.commit();
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);

        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listColumns(int countryId) {
        if (countryId == 0) {
            return new ResponseModel(getClass().getSimpleName(), " ¡ Elija un país, Por favor ! ", null, 200);
        } else {

            LoadValidateReportEntity r = null;
            Connection cn = this.ds.openConnection();
            try {
                cn.setAutoCommit(false);
                CallableStatement cs = cn.prepareCall("{call sp_ReportValidateLoadListColumns(?)}");
                cs.setInt(1, countryId);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    r = new LoadValidateReportEntity();
                    r.setId(rs.getInt("id"));
                    r.setIdCountry(rs.getInt("idCountry"));
                    r.setAvailableDate(rs.getString("availableDate"));
                    r.setModelCode(rs.getString("modelCode"));
                    r.setModelDescription(rs.getString("modelDescription"));
                    r.setDxType(rs.getString("dxType"));
                    r.setSerialEquipo(rs.getString("serialEquipo"));
                    r.setAddress(rs.getString("address"));
                    r.setRegion(rs.getString("region"));
                    r.setCity(rs.getString("city"));
                    r.setDepartment(rs.getString("department"));
                    r.setUserId(rs.getInt("userId"));
                }
                cn.commit();
                cs.close();
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);

            } finally {
                try {
                    cn.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
            return new ResponseModel(getClass().getSimpleName(), "OK", r, 200);
        }
    }
}
