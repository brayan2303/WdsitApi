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
import net.woden.wdsit.entity.DocumentLoadDownloadEntity;
import net.woden.wdsit.entity.DocumentLoadEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.DocumentFileModel;
import net.woden.wdsit.util.Encription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author f.casallas
 */
@Service
public class DocumentLoadService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private Environment en;
    @Autowired
    private Encription eu;

    public ResponseModel create(int userPropertyId, int creationUserId, DocumentLoadEntity d) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentLoadCreate(?,?,?,?,?)}");
            cs.setInt(1, d.getDocumentId());
            cs.setInt(2, userPropertyId);
            cs.setInt(3, d.getVersion());
            cs.setInt(4, creationUserId);
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(5);
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

    public ResponseModel delete(int id) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentLoadDelete(?)}");
            cs.setInt(1, id);
            deletes = cs.executeUpdate();
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

    public ResponseModel list() {
        ArrayList<DocumentLoadEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentLoadList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                DocumentLoadEntity d = new DocumentLoadEntity();
                d.setId(rs.getInt("id"));
                d.setDocumentId(rs.getInt("documentId"));
                d.setDocumentName(rs.getString("documentName"));
                d.setUserPropertyIdentification(rs.getInt("userPropertyIdentification"));
                d.setUserPropertyId(rs.getInt("userPropertyId"));
                d.setUserPropertyName(rs.getString("userPropertyName"));
                d.setDocumentPropertyName(rs.getString("documentPropertyName"));
                d.setExtensionProperty(rs.getString(("extensionProperty")));
                d.setCreationUserId(rs.getInt("creationUserId"));
                d.setCreationDate(rs.getString("creationDate"));
                d.setVersion(rs.getInt("version"));
                d.setUpdateDate(rs.getString("updateDate"));
                d.setActive(rs.getBoolean("active"));

                list.add(d);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel findById(int id) {
        DocumentLoadEntity d = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentLoadFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                d = new DocumentLoadEntity();
                d.setId(rs.getInt("id"));
                d.setDocumentId(rs.getInt("documentId"));
                d.setDocumentName(rs.getString("documentName"));
                d.setUserPropertyId(rs.getInt("userPropertyId"));
                d.setUserPropertyIdentification(rs.getInt("userPropertyIdentification"));
                d.setUserPropertyName(rs.getString("userPropertyName"));
                d.setDocumentPropertyName(rs.getString("documentPropertyName"));
                d.setExtensionProperty(rs.getString(("extensionProperty")));
                d.setDocumentSaveName(rs.getString(("documentSaveName")));
                d.setVersion(rs.getInt("version"));
                d.setActive(rs.getBoolean("active"));
                d.setCreationUserId(rs.getInt("creationUserId"));
                d.setCreationDate(rs.getString("creationDate"));
                d.setUpdateDate(rs.getString("updateDate"));
                d.setCostCenter(rs.getString("costCenter"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", d, 200);
    }

    public ResponseModel findVersion(int identification, int documentId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentLoadFindVersion(?,?,?)}");
            cs.setInt(1, identification);
            cs.setInt(2, documentId);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(3);
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

    public ResponseModel loadFile(int documentId, MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("documentLoad.service.url"));
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        String nombreArchivo = "";
        String extensionArchivo = "";
        String nombreArchivoGuardado = "";

        for (MultipartFile file : files) {
            nombreArchivo = file.getOriginalFilename().split("\\.", 2)[0];
            extensionArchivo = file.getOriginalFilename().split("\\.", 2)[1];
            nombreArchivoGuardado = this.eu.encode(documentId + nombreArchivo + extensionArchivo);
            if (file.getOriginalFilename().split("\\.", 2)[1].equals("png") || file.getOriginalFilename().split("\\.", 2)[1].equals("jpg")) { //
                try {
                    bi = ImageIO.read(file.getInputStream());
                    ImageIO.write(bi, file.getOriginalFilename().split("\\.", 2)[1], new File(directory1 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]));
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            } else {
                try {
                    File f = new File(directory1 + "\\" + nombreArchivoGuardado);
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

        if (load != 0) {
            int inserts = 0;
            Connection cn = this.ds.openConnection();
            try {
                CallableStatement cs = cn.prepareCall("{call sp_DocumentLoadUpdate(?,?,?,?,?)}");
                cs.setInt(1, documentId);
                cs.setString(2, nombreArchivo);
                cs.setString(3, extensionArchivo);
                cs.setString(4, nombreArchivoGuardado);
                cs.registerOutParameter(5, Types.INTEGER);
                cs.execute();
                inserts = cs.getInt(5);
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
        } else {
            return new ResponseModel(getClass().getSimpleName(), "OK", load, 200);
        }
    }

    public ResponseModel listFile(int documenLoadId) {
        DocumentLoadEntity d = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentLoadFindById(?)}");
            cs.setInt(1, documenLoadId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                d = new DocumentLoadEntity();
                d.setId(rs.getInt("id"));
                d.setDocumentId(rs.getInt("documentId"));
                d.setDocumentName(rs.getString("documentName"));
                d.setUserPropertyId(rs.getInt("userPropertyId"));
                d.setUserPropertyIdentification(rs.getInt("userPropertyIdentification"));
                d.setUserPropertyName(rs.getString("userPropertyName"));
                d.setDocumentPropertyName(rs.getString("documentPropertyName"));
                d.setExtensionProperty(rs.getString(("extensionProperty")));
                d.setDocumentSaveName(rs.getString(("documentSaveName")));
                d.setVersion(rs.getInt("version"));
                d.setActive(rs.getBoolean("active"));
                d.setCreationUserId(rs.getInt("creationUserId"));
                d.setCreationDate(rs.getString("creationDate"));
                d.setUpdateDate(rs.getString("updateDate"));
                d.setCostCenter(rs.getString("costCenter"));
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

        ArrayList<DocumentFileModel> list = new ArrayList();
        File f = new File(this.en.getProperty("documentLoad.service.url") + "\\" + d.getDocumentSaveName());
        FileInputStream fis;

        try {
            fis = new FileInputStream(f);
            byte[] bytes = new byte[(int) f.length()];
            fis.read(bytes);
            fis.close();
            DocumentFileModel a = new DocumentFileModel();
            a.setName(d.getDocumentPropertyName());
            a.setFile(bytes);
            a.setType(d.getExtensionProperty());
            list.add(a);
        } catch (FileNotFoundException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel update(int userId, DocumentLoadEntity s) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentLoadUserIdUpdate(?,?,?,?,?,?)}");
            cs.setInt(1, s.getId());
            cs.setString(2, s.getDocumentPropertyName());
            cs.setString(3, s.getExtensionProperty());
            cs.setString(4, s.getDocumentSaveName());
            cs.setInt(5, userId);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(6);
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

    public ResponseModel findByIdentification(String Identification, int TypeDocument) {
        ArrayList<DocumentLoadEntity> list = new ArrayList();;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentLoadDownload(?,?)}");
            cs.setString(1, Identification);
            cs.setInt(2, TypeDocument);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                DocumentLoadEntity d = new DocumentLoadEntity();
                d = new DocumentLoadEntity();
                d.setId(rs.getInt("id"));
                d.setDocumentId(rs.getInt("documentId"));
                d.setDocumentName(rs.getString("documentName"));
                d.setUserPropertyId(rs.getInt("userPropertyId"));
                d.setUserPropertyIdentification(rs.getInt("userPropertyIdentification"));
                d.setUserPropertyName(rs.getString("userPropertyName"));
                d.setDocumentPropertyName(rs.getString("documentPropertyName"));
                d.setExtensionProperty(rs.getString(("extensionProperty")));
                d.setDocumentSaveName(rs.getString(("documentSaveName")));
                d.setVersion(rs.getInt("version"));
                d.setActive(rs.getBoolean("active"));
                d.setCreationUserId(rs.getInt("creationUserId"));
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

    public ResponseModel searchDocs(int documenLoadId) {
        DocumentLoadEntity d = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentLoadFindById(?)}");
            cs.setInt(1, documenLoadId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                d = new DocumentLoadEntity();
                d.setId(rs.getInt("id"));
                d.setDocumentId(rs.getInt("documentId"));
                d.setDocumentName(rs.getString("documentName"));
                d.setUserPropertyId(rs.getInt("userPropertyId"));
                d.setUserPropertyIdentification(rs.getInt("userPropertyIdentification"));
                d.setUserPropertyName(rs.getString("userPropertyName"));
                d.setDocumentPropertyName(rs.getString("documentPropertyName"));
                d.setExtensionProperty(rs.getString(("extensionProperty")));
                d.setDocumentSaveName(rs.getString(("documentSaveName")));
                d.setVersion(rs.getInt("version"));
                d.setActive(rs.getBoolean("active"));
                d.setCreationUserId(rs.getInt("creationUserId"));
                d.setCreationDate(rs.getString("creationDate"));
                d.setUpdateDate(rs.getString("updateDate"));
                d.setCostCenter(rs.getString("costCenter"));
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

        File f = new File(this.en.getProperty("documentLoad.service.url") + "\\" + d.getDocumentSaveName());
        FileInputStream fis;
        DocumentFileModel a = new DocumentFileModel();
        try {
            fis = new FileInputStream(f);
            byte[] bytes = new byte[(int) f.length()];
            fis.read(bytes);
            fis.close();
            a.setName(d.getDocumentPropertyName());
            a.setFile(bytes);
            a.setType(d.getExtensionProperty());
        } catch (FileNotFoundException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } catch (IOException ex) {
            
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", a, 200);
    }

    public ResponseModel registerDownload(DocumentLoadDownloadEntity d) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentLoadRegisterDownloadCreate(?,?,?)}");
            cs.setInt(1, d.getUserId());
            cs.setInt(2, d.getDocumentLoadId());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(3);
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

}
