package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.DaiGoalEntity;
import net.woden.wdsit.model.DaiGoalModel;
import net.woden.wdsit.model.DataModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaiGoalService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(DaiGoalEntity d) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DaiGoalCreate(?,?,?,?,?,?,?)}");
            cs.setInt(1, d.getYear());
            cs.setInt(2, d.getMonthId());
            cs.setInt(3, d.getCountryId());
            cs.setString(4, d.getCountryCustomerId());
            cs.setInt(5, d.getGoal());
            cs.setString(6, d.getCodeFamily());
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(7);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }

    public ResponseModel update(DaiGoalEntity d) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DaiGoalUpdate(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, d.getId());
            cs.setInt(2, d.getYear());
            cs.setInt(3, d.getMonthId());
            cs.setInt(4, d.getCountryId());
             cs.setString(5, d.getCountryCustomerId());
            cs.setInt(6, d.getGoal());
            cs.setString(7, d.getCodeFamily());
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(8);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel delete(int id) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DaiGoalDelete(?)}");
            cs.setInt(1, id);
            deletes = cs.executeUpdate();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }

    public ResponseModel list(int year) {
        ArrayList<DaiGoalEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DaiGoalList(?)}");
            cs.setInt(1, year);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                DaiGoalEntity d = new DaiGoalEntity();
                d.setId(rs.getInt("id"));
                d.setYear(rs.getInt("year"));
                d.setMonthId(rs.getInt("monthId"));
                d.setMonth(rs.getString("month"));
                d.setCountryCustomerId(rs.getString("countryCustomerId"));
                d.setGoal(rs.getInt("goal"));
                d.setCountryId(rs.getInt("countryId"));
                d.setCountry(rs.getString("country"));
                d.setCustomerId(rs.getInt("customerId"));
                d.setCustomer(rs.getString("customer"));
                d.setCodeFamily(rs.getString("codeFamily"));
                list.add(d);
            }
            cn.commit();
            cs.close();
            rs.close();
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

    public ResponseModel dailyOperation(String country) {
        ArrayList<DaiGoalModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DaiGoalDailyOperation(?)}");
            cs.setString(1, country);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                DaiGoalModel d = new DaiGoalModel();
                d.setPais(rs.getString("pais"));
                d.setCliente(rs.getString("cliente"));
                d.setDia(rs.getInt("dia"));
                d.setSemana(rs.getInt("semana"));
                d.setAcumulado(rs.getInt("acumulado"));
                d.setMeta(rs.getInt("meta"));
                d.setCumplimiento(rs.getString("cumplimiento"));
                d.setGap(rs.getInt("gap"));
                list.add(d);
            }
            cn.commit();
            cs.close();
            rs.close();
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

    public ResponseModel entry(String country) {
        DataModel data = new DataModel();
        ArrayList<String> dataX = new ArrayList();
        ArrayList<Integer> dataY = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DaiGoalEntry(?)}");
            cs.setString(1, country);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                dataX.add(rs.getString("dataX"));
                dataY.add(rs.getInt("dataY"));
            }
            data.setDataX(dataX);
            data.setDataY(dataY);
            cn.commit();
            rs.close();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", data, 200);
    }

    public ResponseModel dispatch(String country) {
        DataModel data = new DataModel();
        ArrayList<String> dataX = new ArrayList();
        ArrayList<Integer> dataY = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DaiGoalDispatch(?)}");
            cs.setString(1, country);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                dataX.add(rs.getString("dataX"));
                dataY.add(rs.getInt("dataY"));
            }
            data.setDataX(dataX);
            data.setDataY(dataY);
            cn.commit();
            rs.close();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", data, 200);
    }

    public ResponseModel repair(String country) {
        DataModel data = new DataModel();
        ArrayList<String> dataX = new ArrayList();
        ArrayList<Integer> dataY = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DaiGoalRepair(?)}");
            cs.setString(1, country);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                dataX.add(rs.getString("dataX"));
                dataY.add(rs.getInt("dataY"));
            }
            data.setDataX(dataX);
            data.setDataY(dataY);
            cn.commit();
            rs.close();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", data, 200);
    }
}
