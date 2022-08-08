/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.model.DownloadFilesModel;
import net.woden.wdsit.model.DownloadFilesTigoModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author A.PULIDO
 */
@Service
public class FileGroupValidationService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private Environment en;

    public ResponseModel validationClaro(String validation) {
        System.out.println(validation);

        if (validation.equals("CLARO")) {
            int claroS = 0;
            ArrayList<DownloadFilesModel> listC = new ArrayList();
            File directoryC = new File(this.en.getProperty("descarga.cliente.claro.url"));
            FileInputStream fisC;

            if (directoryC.isDirectory()) {
                for (File f : directoryC.listFiles()) {
                    try {
                        fisC = new FileInputStream(f);
                        byte[] bytes = new byte[(int) f.length()];
                        fisC.read(bytes);
                        fisC.close();
                        DownloadFilesModel d = new DownloadFilesModel();
                        d.setName(f.getName());
                        d.setFile(bytes);
                        d.setType(f.getName().split("\\.", 2)[1]);
                        listC.add(d);
                        claroS = 1;
                    } catch (FileNotFoundException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    } catch (IOException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), listC, 200);
                    }
                }
                if (claroS != 0) {
                    ArrayList<DownloadFilesModel> listCS = new ArrayList();
                    File directoryCS = new File(this.en.getProperty("descarga.cliente.claroSmart.url"));
                    FileInputStream fisCS;

                    if (directoryCS.isDirectory()) {
                        for (File f : directoryCS.listFiles()) {
                            try {
                                fisCS = new FileInputStream(f);
                                byte[] bytes = new byte[(int) f.length()];
                                fisCS.read(bytes);
                                fisCS.close();
                                DownloadFilesModel d = new DownloadFilesModel();
                                d.setName(f.getName());
                                d.setFile(bytes);
                                d.setType(f.getName().split("\\.", 2)[1]);
                                listCS.add(d);
                            } catch (FileNotFoundException ex) {
                                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                            } catch (IOException ex) {
                                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), listCS, 200);
                            }
                        }

                    }
                    return new ResponseModel(getClass().getSimpleName(), "OK", listCS, 200);
                }

            }
            return new ResponseModel(getClass().getSimpleName(), "OK", listC, 200);

        } else if (validation.equals("DIRECTV")) {
            int directvS = 0;
            ArrayList<DownloadFilesModel> listD = new ArrayList();
            File directoryD = new File(this.en.getProperty("descarga.cliente.directv.url"));
            FileInputStream fisD;

            if (directoryD.isDirectory()) {
                for (File f : directoryD.listFiles()) {
                    try {
                        fisD = new FileInputStream(f);
                        byte[] bytes = new byte[(int) f.length()];
                        fisD.read(bytes);
                        fisD.close();
                        DownloadFilesModel d = new DownloadFilesModel();
                        d.setName(f.getName());
                        d.setFile(bytes);
                        d.setType(f.getName().split("\\.", 2)[1]);
                        listD.add(d);
                        directvS = 1;
                    } catch (FileNotFoundException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    } catch (IOException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), listD, 200);
                    }
                }
                if (directvS != 0) {
                    
                    ArrayList<DownloadFilesModel> listDS = new ArrayList();
                    File directoryDS = new File(this.en.getProperty("descarga.cliente.directvSmart.url"));
                    FileInputStream fisDS;

                    if (directoryDS.isDirectory()) {
                        for (File f : directoryDS.listFiles()) {
                            try {
                                fisDS = new FileInputStream(f);
                                byte[] bytes = new byte[(int) f.length()];
                                fisDS.read(bytes);
                                fisDS.close();
                                DownloadFilesModel d = new DownloadFilesModel();
                                d.setName(f.getName());
                                d.setFile(bytes);
                                d.setType(f.getName().split("\\.", 2)[1]);
                                listDS.add(d);
                            } catch (FileNotFoundException ex) {
                                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                            } catch (IOException ex) {
                                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), listDS, 200);
                            }
                        }

                    }
                    return new ResponseModel(getClass().getSimpleName(), "OK", listDS, 200);                    
                }
            }
            return new ResponseModel(getClass().getSimpleName(), "OK", listD, 200);
            
        } else if (validation.equals("HUGHES")) {
            ArrayList<DownloadFilesModel> listH = new ArrayList();
            File directoryH = new File(this.en.getProperty("descarga.cliente.hughes.url"));
            FileInputStream fisH;

            if (directoryH.isDirectory()) {
                for (File f : directoryH.listFiles()) {
                    try {
                        fisH = new FileInputStream(f);
                        byte[] bytes = new byte[(int) f.length()];
                        fisH.read(bytes);
                        fisH.close();
                        DownloadFilesModel d = new DownloadFilesModel();
                        d.setName(f.getName());
                        d.setFile(bytes);
                        d.setType(f.getName().split("\\.", 2)[1]);
                        listH.add(d);
                    } catch (FileNotFoundException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    } catch (IOException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), listH, 200);
                    }
                }

            }
            return new ResponseModel(getClass().getSimpleName(), "OK", listH, 200);
        } else if (validation.equals("TIGO")) {  
            int tigoM = 0;
            ArrayList<DownloadFilesTigoModel> listT = new ArrayList();
            File directoryT = new File(this.en.getProperty("descarga.cliente.tigo.url"));
            FileInputStream fisT;

            if (directoryT.isDirectory()) {
                for (File f : directoryT.listFiles()) {
                    try {
                        fisT = new FileInputStream(f);
                        byte[] bytes = new byte[(int) f.length()];
                        fisT.read(bytes);
                        fisT.close();
                        DownloadFilesTigoModel d = new DownloadFilesTigoModel();
                        d.setName(f.getName());
                        d.setFile(bytes);
                        d.setType(f.getName().split("\\.", 2)[1]);
                        listT.add(d);
                        tigoM = 1;
                    } catch (FileNotFoundException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    } catch (IOException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), listT, 200);
                    }
                }
                if (tigoM != 0) {
                    ArrayList<DownloadFilesTigoModel> listTM = new ArrayList();
                    File directoryTM = new File(this.en.getProperty("descarga.cliente.medellin.url"));
                    FileInputStream fisTM;

                    if (directoryTM.isDirectory()) {
                        for (File f : directoryTM.listFiles()) {
                            try {
                                fisTM = new FileInputStream(f);
                                byte[] bytes = new byte[(int) f.length()];
                                fisTM.read(bytes);
                                fisTM.close();
                                DownloadFilesTigoModel d = new DownloadFilesTigoModel();
                                d.setName(f.getName());
                                d.setFile(bytes);
                                d.setType(f.getName().split("\\.", 2)[1]);
                                listTM.add(d);
                            } catch (FileNotFoundException ex) {
                                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                            } catch (IOException ex) {
                                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), listTM, 200);
                            }
                        }

                    }
                    return new ResponseModel(getClass().getSimpleName(), "OK", listT, 200);
                    
                }
            }
            return new ResponseModel(getClass().getSimpleName(), "OK", listT, 200);
            
        } else if (validation.equals("ETB")) {
            
            ArrayList<DownloadFilesModel> listE = new ArrayList();
            File directoryE = new File(this.en.getProperty("descarga.cliente.etb.url"));
            FileInputStream fisE;

            if (directoryE.isDirectory()) {
                for (File f : directoryE.listFiles()) {
                    try {
                        fisE = new FileInputStream(f);
                        byte[] bytes = new byte[(int) f.length()];
                        fisE.read(bytes);
                        fisE.close();
                        DownloadFilesModel d = new DownloadFilesModel();
                        d.setName(f.getName());
                        d.setFile(bytes);
                        d.setType(f.getName().split("\\.", 2)[1]);
                        listE.add(d);
                    } catch (FileNotFoundException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    } catch (IOException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), listE, 200);
                    }
                }

            }
            return new ResponseModel(getClass().getSimpleName(), "OK", listE, 200);
            
        } else if (validation.equals("RADIO-TECH")) {
            ArrayList<DownloadFilesModel> listRT = new ArrayList();
            File directoryRT = new File(this.en.getProperty("descarga.cliente.radioTech.url"));
            FileInputStream fisRT;

            if (directoryRT.isDirectory()) {
                for (File f : directoryRT.listFiles()) {
                    try {
                        fisRT = new FileInputStream(f);
                        byte[] bytes = new byte[(int) f.length()];
                        fisRT.read(bytes);
                        fisRT.close();
                        DownloadFilesModel d = new DownloadFilesModel();
                        d.setName(f.getName());
                        d.setFile(bytes);
                        d.setType(f.getName().split("\\.", 2)[1]);
                        listRT.add(d);
                    } catch (FileNotFoundException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    } catch (IOException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), listRT, 200);
                    }
                }

            }
            return new ResponseModel(getClass().getSimpleName(), "OK", listRT, 200);
            
            
        } else if (validation.equals("PLATAFORMA MOVIL")) {
            ArrayList<DownloadFilesTigoModel> listPM = new ArrayList();
            File directoryPM = new File(this.en.getProperty("descarga.cliente.plataforma.url"));
            FileInputStream fisPM;

            if (directoryPM.isDirectory()) {
                for (File f : directoryPM.listFiles()) {
                    try {
                        fisPM = new FileInputStream(f);
                        byte[] bytes = new byte[(int) f.length()];
                        fisPM.read(bytes);
                        fisPM.close();
                        DownloadFilesTigoModel d = new DownloadFilesTigoModel();
                        d.setName(f.getName());
                        d.setFile(bytes);
                        d.setType(f.getName().split("\\.", 2)[1]);
                        listPM.add(d);
                    } catch (FileNotFoundException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    } catch (IOException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), listPM, 200);
                    }
                }

            }
            return new ResponseModel(getClass().getSimpleName(), "OK", listPM, 200);            
            
        } else if (validation.equals("RED EXTERNA")) {
            ArrayList<DownloadFilesTigoModel> listRE = new ArrayList();
            File directoryRE = new File(this.en.getProperty("descarga.cliente.red.url"));
            FileInputStream fisRE;

            if (directoryRE.isDirectory()) {
                for (File f : directoryRE.listFiles()) {
                    try {
                        fisRE = new FileInputStream(f);
                        byte[] bytes = new byte[(int) f.length()];
                        fisRE.read(bytes);
                        fisRE.close();
                        DownloadFilesTigoModel d = new DownloadFilesTigoModel();
                        d.setName(f.getName());
                        d.setFile(bytes);
                        d.setType(f.getName().split("\\.", 2)[1]);
                        listRE.add(d);
                    } catch (FileNotFoundException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    } catch (IOException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), listRE, 200);
                    }
                }

            }
            return new ResponseModel(getClass().getSimpleName(), "OK", listRE, 200);            
            
        } else {
            return new ResponseModel(getClass().getSimpleName(), "El cliente seleccionado no tiene archivo", null, 200);
        }
    }
}
