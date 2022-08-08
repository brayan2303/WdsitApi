/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.model.DownloadFilesModel;
import net.woden.wdsit.model.DownloadFilesTigoModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class DownloadFilesService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private Environment en;

    public ResponseModel listFileClaro() {
        ArrayList<DownloadFilesModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("descarga.cliente.claro.url"));
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    DownloadFilesModel d = new DownloadFilesModel();
                    d.setName(f.getName());
                    d.setFile(bytes);
                    d.setType(f.getName().split("\\.", 2)[1]);
                    list.add(d);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listFileClaroSmart() {
        ArrayList<DownloadFilesModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("descarga.cliente.claroSmart.url"));
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    DownloadFilesModel d = new DownloadFilesModel();
                    d.setName(f.getName());
                    d.setFile(bytes);
                    d.setType(f.getName().split("\\.", 2)[1]);
                    list.add(d);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listFileClaroFont() {
        ArrayList<DownloadFilesModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("descarga.cliente.claroFon.url"));
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    DownloadFilesModel d = new DownloadFilesModel();
                    d.setName(f.getName());
                    d.setFile(bytes);
                    d.setType(f.getName().split("\\.", 2)[1]);
                    list.add(d);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listFileDirectv() {
        ArrayList<DownloadFilesModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("descarga.cliente.directv.url"));
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    DownloadFilesModel d = new DownloadFilesModel();
                    d.setName(f.getName());
                    d.setFile(bytes);
                    d.setType(f.getName().split("\\.", 2)[1]);
                    list.add(d);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listFileDirectvSmart() {
        ArrayList<DownloadFilesModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("descarga.cliente.directvSmart.url"));
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    DownloadFilesModel d = new DownloadFilesModel();
                    d.setName(f.getName());
                    d.setFile(bytes);
                    d.setType(f.getName().split("\\.", 2)[1]);
                    list.add(d);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listFileEtb() {
        ArrayList<DownloadFilesModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("descarga.cliente.etb.url"));
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    DownloadFilesModel d = new DownloadFilesModel();
                    d.setName(f.getName());
                    d.setFile(bytes);
                    d.setType(f.getName().split("\\.", 2)[1]);
                    list.add(d);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listFileTigo() {
        ArrayList<DownloadFilesTigoModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("descarga.cliente.tigo.url"));
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    DownloadFilesTigoModel d = new DownloadFilesTigoModel();
                    d.setName(f.getName());
                    d.setFile(bytes);
                    d.setType(f.getName().split("\\.", 2)[1]);
                    list.add(d);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listFileHughes() {
        ArrayList<DownloadFilesModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("descarga.cliente.hughes.url"));
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    DownloadFilesModel d = new DownloadFilesModel();
                    d.setName(f.getName());
                    d.setFile(bytes);
                    d.setType(f.getName().split("\\.", 2)[1]);
                    list.add(d);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listFileTigoMedellin() {
        ArrayList<DownloadFilesTigoModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("descarga.cliente.medellin.url"));
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    DownloadFilesTigoModel d = new DownloadFilesTigoModel();
                    d.setName(f.getName());
                    d.setFile(bytes);
                    d.setType(f.getName().split("\\.", 2)[1]);
                    list.add(d);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listFileRedExterna() {
        ArrayList<DownloadFilesTigoModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("descarga.cliente.red.url"));
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    DownloadFilesTigoModel d = new DownloadFilesTigoModel();
                    d.setName(f.getName());
                    d.setFile(bytes);
                    d.setType(f.getName().split("\\.", 2)[1]);
                    list.add(d);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listPlataforma() {
        ArrayList<DownloadFilesTigoModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("descarga.cliente.plataforma.url"));
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    DownloadFilesTigoModel d = new DownloadFilesTigoModel();
                    d.setName(f.getName());
                    d.setFile(bytes);
                    d.setType(f.getName().split("\\.", 2)[1]);
                    list.add(d);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel findCustomerByPersonIdList(int personId) {
        GenCustomerEntity customer = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFindCustomerByPersonIdList(?)}");
            cs.setInt(1, personId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                customer = new GenCustomerEntity();
                customer.setId(rs.getInt("id"));
                customer.setCode(rs.getString("code"));
                customer.setDescription(rs.getString("description"));
                customer.setIncomeActive(rs.getBoolean("incomeActive"));
                customer.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", customer, 200);
    }
}
