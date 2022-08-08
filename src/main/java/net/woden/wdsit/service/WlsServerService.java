package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.connection.DataSourceWlsConnection;
import net.woden.wdsit.entity.WlsServerEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.WlsServerColumnModel;
import net.woden.wdsit.model.WlsServerDataBaseModel;
import net.woden.wdsit.model.WlsServerTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WlsServerService {

    @Autowired
    DataSourceConnection ds;
    @Autowired
    DataSourceWlsConnection dsw;

    public ResponseModel create(WlsServerEntity w) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsServerCreate(?,?,?,?,?,?,?)}");
            cs.setString(1, w.getIp());
            cs.setInt(2, w.getPort());
            cs.setString(3, w.getUserName());
            cs.setString(4, w.getPassword());
            cs.setString(5, w.getType());
            cs.setString(6, w.getSgdb());
            cs.registerOutParameter(7, Types.VARCHAR);
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

    public ResponseModel update(WlsServerEntity w) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsServerUpdate(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, w.getId());
            cs.setString(2, w.getIp());
            cs.setInt(3, w.getPort());
            cs.setString(4, w.getUserName());
            cs.setString(5, w.getPassword());
            cs.setString(6, w.getType());
            cs.setString(7, w.getSgdb());
            cs.setBoolean(8, w.isActive());
            cs.registerOutParameter(9, Types.VARCHAR);
            cs.execute();
            updates = cs.getInt(9);
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
            CallableStatement cs = cn.prepareCall("{call sp_WlsServerDelete(?)}");
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

    public ResponseModel dataBaseCreate(String dataBaseName) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsServerDataBaseCreate(?)}");
            cs.setString(1, dataBaseName);
            inserts = cs.executeUpdate();
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

    public ResponseModel find(String ip) {
        WlsServerEntity w = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsServerFind(?)}");
            cs.setString(1, ip);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                w = new WlsServerEntity();
                w.setIp(rs.getString("ip"));
                w.setPort(rs.getInt("port"));
                w.setUserName(rs.getString("userName"));
                w.setPassword(rs.getString("password"));
                w.setType(rs.getString("type"));
                w.setSgdb(rs.getString("sgdb"));
                w.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", w, 200);
    }

    public ResponseModel list() {
        ArrayList<WlsServerEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsServerList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                WlsServerEntity w = new WlsServerEntity();
                w.setId(rs.getInt("id"));
                w.setIp(rs.getString("ip"));
                w.setPort(rs.getInt("port"));
                w.setUserName(rs.getString("userName"));
                w.setPassword(rs.getString("password"));
                w.setType(rs.getString("type"));
                w.setSgdb(rs.getString("sgdb"));
                w.setActive(rs.getBoolean("active"));
                list.add(w);
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

    public ResponseModel dataBase(String ip) {
        ArrayList<WlsServerDataBaseModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsServerDataBase(?)}");
            cs.setString(1, ip);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                WlsServerDataBaseModel w = new WlsServerDataBaseModel();
                w.setName(rs.getString("name"));
                w.setMasterFile(rs.getString("masterFile"));
                w.setMasterSize(rs.getInt("masterSize"));
                w.setLogsFile(rs.getString("logsFile"));
                w.setLogsSize(rs.getInt("logsSize"));
                list.add(w);
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

    public ResponseModel table(String ip, String dataBase) {
        ArrayList<WlsServerTableModel> list = new ArrayList();
        WlsServerEntity server = (WlsServerEntity) this.find(ip).getObject();
        Connection cn = (Connection) this.dsw.openConnection(server, dataBase).getObject();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsServerTable(?)}");
            cs.setString(1, dataBase);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                WlsServerTableModel w = new WlsServerTableModel();
                w.setName(rs.getString("name"));
                w.setRows(rs.getInt("rows"));
                w.setReserved(rs.getString("reserved"));
                w.setData(rs.getString("data"));
                w.setIndex(rs.getString("index"));
                w.setUnused(rs.getString("unused"));
                list.add(w);
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

    public ResponseModel column(String dataBaseName, String tableName) {
        ArrayList<WlsServerColumnModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsServerColumn(?,?)}");
            cs.setString(1, dataBaseName);
            cs.setString(2, tableName);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                WlsServerColumnModel w = new WlsServerColumnModel();
                w.setName(rs.getString("name"));
                w.setDataType(rs.getString("dataType"));
                w.setLength(rs.getString("length"));
                w.setIsNull(rs.getString("isNull"));
                list.add(w);
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

    public ResponseModel testConnection(String ip, String dataBase) {
        WlsServerEntity server = (WlsServerEntity) this.find(ip).getObject();
        Connection cn = (Connection) this.dsw.openConnection(server, dataBase).getObject();
        if (cn != null) {
            try {
                cn.setAutoCommit(false);
                PreparedStatement ps = cn.prepareStatement("select count(*) from INFORMATION_SCHEMA.TABLES");
                ResultSet rs = ps.executeQuery();
                cn.commit();
                ps.close();
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
        }else{
            return new ResponseModel(getClass().getSimpleName(), "OK", 0, 200);
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", 1, 200);
    }
}
