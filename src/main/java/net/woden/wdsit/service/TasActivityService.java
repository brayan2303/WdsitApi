package net.woden.wdsit.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.TasActivityEntity;
import net.woden.wdsit.model.DevActivityModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TasActivityService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(TasActivityEntity t) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TasActivityCreate(?,?,?,?,?,?)}");
            cs.setString(1, t.getTitle());
            cs.setString(2, t.getStatus());
            cs.setString(3, t.getNote());
            cs.setInt(4, t.getTaskId());
            cs.setInt(5, t.getPersonId());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(6);
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

    public ResponseModel update(TasActivityEntity t) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TasActivityUpdate(?,?,?,?,?,?,?)}");
            cs.setInt(1, t.getId());
            cs.setString(2, t.getTitle());
            cs.setString(3, t.getStatus());
            cs.setString(4, t.getNote());
            cs.setInt(5, t.getTaskId());
            cs.setInt(6, t.getPersonId());
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(7);
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

    public ResponseModel delete(int activityId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TasActivityDelete(?)}");
            cs.setInt(1, activityId);
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

    public ResponseModel close(int activityId) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TasActivityClose(?,?)}");
            cs.setInt(1, activityId);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(2);
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

    public ResponseModel findClose(int taskId) {
        int total = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TasActivityFindClose(?)}");
            cs.setInt(1, taskId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }

    public ResponseModel findById(int activityId) {
        TasActivityEntity t = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TasActivityFindById(?)}");
            cs.setInt(1, activityId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                t = new TasActivityEntity();
                t.setId(rs.getInt("id"));
                t.setTitle(rs.getString("title"));
                t.setStatus(rs.getString("status"));
                t.setNote(rs.getString("note"));
                t.setTaskId(rs.getInt("taskId"));
                t.setPersonId(rs.getInt("personId"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", t, 200);
    }

    public ResponseModel list(int taskId) {
        ArrayList<TasActivityEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TasActivityList(?)}");
            cs.setInt(1, taskId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                TasActivityEntity t = new TasActivityEntity();
                t.setId(rs.getInt("id"));
                t.setTitle(rs.getString("title"));
                t.setStatus(rs.getString("status"));
                t.setNote(rs.getString("note"));
                t.setTaskId(rs.getInt("taskId"));
                t.setPersonId(rs.getInt("personId"));
                list.add(t);
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

    public ResponseModel loadFile(String taskId, String activityId, MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("tareas.actividades.url") + "\\" + taskId);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        File directory2 = new File(this.en.getProperty("tareas.actividades.url") + "\\" + taskId + "\\" + activityId);
        if (!directory2.isDirectory()) {
            directory2.mkdir();
        }
        for (MultipartFile file : files) {
            if (file.getOriginalFilename().split("\\.", 2)[1].equals("png") || file.getOriginalFilename().split("\\.", 2)[1].equals("jpg")) {
                try {
                    bi = ImageIO.read(file.getInputStream());
                    ImageIO.write(bi, file.getOriginalFilename().split("\\.", 2)[1], new File(directory2 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]));
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            } else {
                try {
                    File f = new File(directory2 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]);
                    f.createNewFile();
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(file.getBytes());
                    fos.close();
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", load, 200);
    }

    public ResponseModel listFile(String taskId) {
        ArrayList<DevActivityModel> list = new ArrayList();
        File directoryTask = new File(this.en.getProperty("tareas.actividades.url") + "\\" + taskId);
        FileInputStream fis;
        if (directoryTask.isDirectory()) {
            for (File f : directoryTask.listFiles()) {
                if (f.isDirectory()) {
                    for (File a : f.listFiles()) {
                        try {
                            fis = new FileInputStream(a);
                            byte[] bytes = new byte[(int) a.length()];
                            fis.read(bytes);
                            fis.close();
                            DevActivityModel d = new DevActivityModel();
                            d.setTaskId(Integer.valueOf(taskId));
                            d.setActivityId(Integer.valueOf(f.getName()));
                            d.setName(a.getName());
                            d.setFile(bytes);
                            list.add(d);
                        } catch (FileNotFoundException ex) {
                            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                        } catch (IOException ex) {
                            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                        }
                    }
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel deleteFile(String taskId, String activity, String fileName) {
        int deletes = 0;
        File directory = new File(this.en.getProperty("tareas.actividades.url") + "\\" + taskId + "\\" + activity);
        if (directory.isDirectory()) {
            for (String f : directory.list()) {
                if (f.equals(fileName)) {
                    File file = new File(directory + "/" + f);
                    if (file.delete()) {
                        deletes = 1;
                    }
                    break;
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }

    public ResponseModel deleteFileByActivityId(String taskId, String activityId) {
        File directoryTask = new File(this.en.getProperty("tareas.actividades.url") + "\\" + taskId);
        if (directoryTask.isDirectory()) {
            File directoryActivity = new File(this.en.getProperty("tareas.actividades.url") + "\\" + taskId + "\\" + activityId);
            if (directoryActivity.isDirectory()) {
                for (File f : directoryActivity.listFiles()) {
                    f.delete();
                }
                directoryActivity.delete();
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", 1, 200);
    }

    public ResponseModel deleteFileByTaskId(String taskId) {
        File directoryTask = new File(this.en.getProperty("tareas.actividades.url") + "\\" + taskId);
        if (directoryTask.isDirectory()) {
            for (File a : directoryTask.listFiles()) {
                if (a.isDirectory()) {
                    for (File f : a.listFiles()) {
                        f.delete();
                    }
                    a.delete();
                }
            }
            directoryTask.delete();
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", 1, 200);
    }
}
