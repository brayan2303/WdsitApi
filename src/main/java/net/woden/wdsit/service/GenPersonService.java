package net.woden.wdsit.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenPersonEntity;
import net.woden.wdsit.model.GenPersonUpdatePasswordModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.ResponseTokenModel;
import net.woden.wdsit.util.EncryptUtility;
import net.woden.wdsit.util.PasswordEncoder;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GenPersonService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptUtility ec;

    public ResponseModel create(GenPersonEntity g) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonCreate(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, g.getIdentification());
            cs.setString(2, g.getFirstName());
            cs.setString(3, g.getLastName());
            cs.setString(4, g.getUserName());
            cs.setString(5, this.passwordEncoder.encode(g.getPassword()));
            cs.setString(6, g.getMail());
            cs.setInt(7, g.getCenterCostId());
            cs.setInt(8, g.getPositionId());
            cs.setInt(9, g.getCityId());
            cs.registerOutParameter(10, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(10);
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

    public ResponseModel update(GenPersonEntity g) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonUpdate(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, g.getId());
            cs.setInt(2, g.getIdentification());
            cs.setString(3, g.getFirstName());
            cs.setString(4, g.getLastName());
            cs.setString(5, g.getUserName());
            cs.setString(6, this.passwordEncoder.encode(g.getPassword()));
            cs.setString(7, g.getMail());
            cs.setInt(8, g.getCenterCostId());
            cs.setInt(9, g.getPositionId());
            cs.setInt(10, g.getCityId());
            cs.setBoolean(11, g.isActive());
            cs.registerOutParameter(12, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(12);
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

    public ResponseModel updatePassword(int id, GenPersonUpdatePasswordModel pm) {
        Connection cn = this.ds.openConnection();
        GenPersonEntity g = null;

        try {
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                g = new GenPersonEntity();
                g.setId(rs.getInt("id"));
                g.setIdentification(rs.getInt("identification"));
                g.setFirstName(rs.getString("firstName"));
                g.setLastName(rs.getString("lastName"));
                g.setUserName(rs.getString("userName"));
                g.setPassword(rs.getString("password"));
                g.setMail(rs.getString("mail"));
                g.setCreationDate(rs.getDate("creationDate"));
                g.setLoginDate(rs.getDate("loginDate"));
                g.setCenterCostId(rs.getInt("centerCostId"));
                g.setCenterCost(rs.getString("centerCost"));
                g.setPositionId(rs.getInt("positionId"));
                g.setPosition(rs.getString("position"));
                g.setCountryId(rs.getInt("countryId"));
                g.setDepartmentId(rs.getInt("departmentId"));
                g.setCityId(rs.getInt("cityId"));
                g.setCity(rs.getString("city"));
                g.setActive(rs.getBoolean("active"));
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }

        if (g != null) {
            int updates = 0;
            if (pm.getPasswordNew().equals("") == false && pm.getPasswordOld().equals("") == false) {
                boolean result = this.passwordEncoder.match(this.ec.decode(pm.getPasswordOld()), g.getPassword());
                if (result == true) {
                    cn = this.ds.openConnection();
                    try {
                        cn.setAutoCommit(false);
                        CallableStatement cs = cn.prepareCall("{call sp_GenPersonUpdatePassword(?,?,?)}");
                        cs.setInt(1, id);
                        cs.setString(2, this.passwordEncoder.encode(this.ec.decode(pm.getPasswordNew())));
                        cs.registerOutParameter(3, Types.INTEGER);
                        cs.execute();
                        updates = cs.getInt(3);
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
                } else {
                    return new ResponseModel(getClass().getSimpleName(), "Contraseña no valida!", null, 200);
                }

            } else {
                return new ResponseModel(getClass().getSimpleName(), "Contraseñas no validas!", null, 200);
            }
        } else {
            return new ResponseModel(getClass().getSimpleName(), "Usuario no encontrado", null, 200);
        }
    }

    public ResponseModel delete(int genPersonId, boolean active) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonDelete(?,?,?)}");
            cs.setInt(1, genPersonId);
            cs.setBoolean(2, active);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            deletes = cs.getInt(3);
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

    public ResponseTokenModel login(String userName, String password, String country) {
        GenPersonEntity g = null;
        Connection cn = this.ds.openConnection();
        String passDB = "";
        try {
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonLogin(?,?)}");
            cs.setString(1, this.ec.decode(userName));
            cs.setString(2, this.ec.decode(country));
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                g = new GenPersonEntity();
                g.setId(rs.getInt("id"));
                g.setIdentification(rs.getInt("identification"));
                g.setFirstName(rs.getString("firstName"));
                g.setLastName(rs.getString("lastName"));
                g.setUserName(rs.getString("userName"));
                g.setActive(rs.getBoolean("active"));
                passDB = rs.getString("password");
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseTokenModel(getClass().getSimpleName(), ex.getMessage(), null, null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseTokenModel(getClass().getSimpleName(), ex.getMessage(), null, null, 200);
            }
        }
        if(passDB.equals("SIN PAIS") == true){
            return new ResponseTokenModel(getClass().getSimpleName(), "El usuario no tiene permisos para el pais seleccionado!", null, null, 200);
        } else if(passDB.equals("") == false){
            boolean result = false;
            result = this.passwordEncoder.match(this.ec.decode(password), passDB);
            if (result == true) {
                return new ResponseTokenModel(getClass().getSimpleName(), "OK", g, null, 200);
            } else {
                return new ResponseTokenModel(getClass().getSimpleName(), "Usuario y/o contraseña incorrecta", null, null, 200);
            }
        } else {
            return new ResponseTokenModel(getClass().getSimpleName(), "El usuario no existe", null, null, 200);
        }
    }

    public ResponseModel findById(int id) {
        GenPersonEntity g = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                g = new GenPersonEntity();
                g.setId(rs.getInt("id"));
                g.setIdentification(rs.getInt("identification"));
                g.setFirstName(rs.getString("firstName"));
                g.setLastName(rs.getString("lastName"));
                g.setUserName(rs.getString("userName"));
                g.setPassword(rs.getString("password"));
                g.setMail(rs.getString("mail"));
                g.setCreationDate(rs.getDate("creationDate"));
                g.setLoginDate(rs.getDate("loginDate"));
                g.setCenterCostId(rs.getInt("centerCostId"));
                g.setCenterCost(rs.getString("centerCost"));
                g.setPositionId(rs.getInt("positionId"));
                g.setPosition(rs.getString("position"));
                g.setCountryId(rs.getInt("countryId"));
                g.setDepartmentId(rs.getInt("departmentId"));
                g.setCityId(rs.getInt("cityId"));
                g.setCity(rs.getString("city"));
                g.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), g == null ? "Usuario no encontrado" : "OK", g, 200);
    }

    public ResponseModel findByName(String name) {
        GenPersonEntity g = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonFindByName(?)}");
            cs.setString(1, name);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                g = new GenPersonEntity();
                g.setId(rs.getInt("id"));
                g.setIdentification(rs.getInt("identification"));
                g.setFirstName(rs.getString("firstName"));
                g.setLastName(rs.getString("lastName"));
                g.setUserName(rs.getString("userName"));
                g.setPassword(rs.getString("password"));
                g.setMail(rs.getString("mail"));
                g.setCreationDate(rs.getDate("creationDate"));
                g.setLoginDate(rs.getDate("loginDate"));
                g.setCenterCostId(rs.getInt("centerCostId"));
                g.setCenterCost(rs.getString("centerCost"));
                g.setPositionId(rs.getInt("positionId"));
                g.setPosition(rs.getString("position"));
                g.setCountryId(rs.getInt("countryId"));
                g.setDepartmentId(rs.getInt("departmentId"));
                g.setCityId(rs.getInt("cityId"));
                g.setCity(rs.getString("city"));
                g.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), g == null ? "Usuario no encontrado" : "OK", g, 200);
    }

    public ResponseModel findByIdentification(int identification) {
        GenPersonEntity g = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonFindByIdentification(?)}");
            cs.setInt(1, identification);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                g = new GenPersonEntity();
                g.setId(rs.getInt("id"));
                g.setIdentification(rs.getInt("identification"));
                g.setFirstName(rs.getString("firstName"));
                g.setLastName(rs.getString("lastName"));
                g.setUserName(rs.getString("userName"));
                g.setPassword(rs.getString("password"));
                g.setMail(rs.getString("mail"));
                g.setCreationDate(rs.getDate("creationDate"));
                g.setLoginDate(rs.getDate("loginDate"));
                g.setCenterCostId(rs.getInt("centerCostId"));
                g.setCenterCost(rs.getString("centerCost"));
                g.setPositionId(rs.getInt("positionId"));
                g.setPosition(rs.getString("position"));
                g.setCityId(rs.getInt("cityId"));
                g.setCity(rs.getString("city"));
                g.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), g == null ? "Usuario no encontrado" : "OK", g, 200);
    }

    public ResponseModel findByPosition(String position) {
        ArrayList<GenPersonEntity> lista = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonFindByPosition(?)}");
            cs.setString(1, position);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenPersonEntity g = new GenPersonEntity();
                g.setId(rs.getInt("id"));
                g.setFirstName(rs.getString("firstName"));
                g.setLastName(rs.getString("lastName"));
                g.setUserName(rs.getString("userName"));
                lista.add(g);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lista, 200);
    }

    public ResponseModel list() {
        ArrayList<GenPersonEntity> lista = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenPersonEntity g = new GenPersonEntity();
                g.setId(rs.getInt("id"));
                g.setIdentification(rs.getInt("identification"));
                g.setFirstName(rs.getString("firstName"));
                g.setLastName(rs.getString("lastName"));
                g.setUserName(rs.getString("userName"));
                g.setPassword(rs.getString("password"));
                g.setMail(rs.getString("mail"));
                g.setCreationDate(rs.getDate("creationDate"));
                g.setLoginDate(rs.getDate("loginDate"));
                g.setCenterCostId(rs.getInt("centerCostId"));
                g.setCenterCost(rs.getString("centerCost"));
                g.setPositionId(rs.getInt("positionId"));
                g.setPosition(rs.getString("position"));
                g.setCityId(rs.getInt("cityId"));
                g.setCity(rs.getString("city"));
                g.setActive(rs.getBoolean("active"));
                lista.add(g);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lista, 200);
    }

    public ResponseModel listDirector() {
        ArrayList<GenPersonEntity> lista = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonListDirector()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenPersonEntity g = new GenPersonEntity();
                g.setId(rs.getInt("id"));
                g.setIdentification(rs.getInt("identification"));
                g.setFirstName(rs.getString("firstName"));
                g.setLastName(rs.getString("lastName"));
                g.setUserName(rs.getString("userName"));
                g.setPassword(rs.getString("password"));
                g.setMail(rs.getString("mail"));
                g.setCreationDate(rs.getDate("creationDate"));
                g.setLoginDate(rs.getDate("loginDate"));
                g.setCenterCostId(rs.getInt("centerCostId"));
                g.setCenterCost(rs.getString("centerCost"));
                g.setPositionId(rs.getInt("positionId"));
                g.setPosition(rs.getString("position"));
                g.setCityId(rs.getInt("cityId"));
                g.setCity(rs.getString("city"));
                g.setActive(rs.getBoolean("active"));
                lista.add(g);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lista, 200);
    }

    public ResponseModel loadImage(int personId, MultipartFile file) {
        int load = 0;
        BufferedImage bi;
        BufferedImage biScale;
        File directory = new File(this.en.getProperty("general.fotos.url"));
        if (directory.isDirectory()) {
            try {
                bi = ImageIO.read(file.getInputStream());
                biScale = Scalr.resize(bi, 200);
                ImageIO.write(biScale, file.getOriginalFilename().split("\\.", 2)[1], new File(directory + "\\" + String.valueOf(personId) + "." + file.getOriginalFilename().split("\\.", 2)[1]));
                load = 1;
            } catch (IOException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", load, 200);
    }

    public ResponseModel findImage(int personId) {
        File directory = new File(this.en.getProperty("general.fotos.url"));
        FileInputStream fis;
        byte[] bytes = null;
        if (directory.isDirectory()) {
            for (String f : directory.list()) {
                if (f.split("\\.", 2)[0].equals(String.valueOf(personId))) {
                    File file = new File(directory + "/" + f);
                    try {
                        fis = new FileInputStream(file);
                        bytes = new byte[(int) file.length()];
                        fis.read(bytes);
                        fis.close();
                    } catch (FileNotFoundException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    } catch (IOException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    }
                    break;
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", bytes, 200);
    }

    public ResponseModel updatePasswords() {
        ArrayList<GenPersonEntity> lista = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenPersonEntity g = new GenPersonEntity();
                g.setId(rs.getInt("id"));
                g.setIdentification(rs.getInt("identification"));
                g.setFirstName(rs.getString("firstName"));
                g.setLastName(rs.getString("lastName"));
                g.setUserName(rs.getString("userName"));
                g.setPassword(rs.getString("password"));
                g.setMail(rs.getString("mail"));
                g.setCreationDate(rs.getDate("creationDate"));
                g.setLoginDate(rs.getDate("loginDate"));
                g.setCenterCostId(rs.getInt("centerCostId"));
                g.setCenterCost(rs.getString("centerCost"));
                g.setPositionId(rs.getInt("positionId"));
                g.setPosition(rs.getString("position"));
                g.setCityId(rs.getInt("cityId"));
                g.setCity(rs.getString("city"));
                g.setActive(rs.getBoolean("active"));
                lista.add(g);
            }
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
        int updates = 0;
        for (int i = 0; i < lista.size(); i++) {
            GenPersonEntity a = new GenPersonEntity();
            a = lista.get(i);
            cn = this.ds.openConnection();
            try {
                cn.setAutoCommit(false);
                CallableStatement cs = cn.prepareCall("{call sp_GenPersonUpdatePassword(?,?,?)}");
                cs.setInt(1, a.getId());
                cs.setString(2, this.passwordEncoder.encode(a.getPassword()));
                cs.registerOutParameter(3, Types.INTEGER);
                cs.execute();
                updates += cs.getInt(3);
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
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }
    
    
        public ResponseModel listCountry(int countryId) {
        ArrayList<GenPersonEntity> lista = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonListCountry(?)}");
            cs.setInt(1, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenPersonEntity g = new GenPersonEntity();
                g.setId(rs.getInt("id"));
                g.setIdentification(rs.getInt("identification"));
                g.setFirstName(rs.getString("firstName"));
                g.setLastName(rs.getString("lastName"));
                g.setUserName(rs.getString("userName"));
                g.setPassword(rs.getString("password"));
                g.setMail(rs.getString("mail"));
                g.setCreationDate(rs.getDate("creationDate"));
                g.setLoginDate(rs.getDate("loginDate"));
                g.setCenterCostId(rs.getInt("centerCostId"));
                g.setCenterCost(rs.getString("centerCost"));
                g.setPositionId(rs.getInt("positionId"));
                g.setPosition(rs.getString("position"));
                g.setCityId(rs.getInt("cityId"));
                g.setCity(rs.getString("city"));
                g.setCountryId(rs.getInt("countryId"));
                g.setActive(rs.getBoolean("active"));
                lista.add(g);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lista, 200);
    }
}
