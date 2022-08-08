/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import org.springframework.stereotype.Component;

/**
 *
 * @author b.algecira
 */
@Component
public class LoadRuleGeneralService {

    public String concatenar(String variable, String l, String funcion) {
        String datoGeneral = "";
        if (null == funcion) {
            return datoGeneral = "SIN REGLA";
        } else {
            switch (funcion) {
                case "CONCATENAR ATRAS": {
                    datoGeneral = l + variable;
                }
                case "CONCATENAR ADELANTE": {
                    datoGeneral = variable + l;
                }

            }
        }
        return datoGeneral;
    }

    public String reemplazar(String variable1, String variable2, String l, String funcion) {
        String datoGeneral = "";
        String result = "";
        if (null == funcion) {
            return datoGeneral = "SIN REGLA";
        } else {
            switch (funcion) {
                case "DATO2=DATO1": {
                    String replace = datoGeneral.replace(variable2, variable1);
                    result = replace;
                }
                case "DATO1=DATO2": {
                    String replace = datoGeneral.replace(variable1, variable2);
                    result = replace;

                }

            }
        }
        return result;
    }

    public String extraer(String l, String funcion) {
        String datoGeneral = "";
        if (null == funcion) {
            return datoGeneral = "SIN REGLA";
        } else {
            switch (funcion) {
                case "EXTRAE": {
                    datoGeneral = l;
                }
            }
        }
        return datoGeneral;
    }

    public String generarTexto(String l, String funcion) {
        String datoGeneral = "";
        if (null == funcion) {
            return datoGeneral = "SIN REGLA";
        } else {
            switch (funcion) {
                case "TEXTO": {
                    datoGeneral = l;
                }
            }
        }
        return datoGeneral;

    }

    public String concaternarNumero(String variable1, String l, String funcion) {
        String datoGeneral = "";
        if (null == funcion) {
            return datoGeneral = "SIN REGLA";
        } else {
            switch (funcion) {
                case "CONCATENAR NUMERO ADELANTE": {
                    datoGeneral = variable1 + l;
                }
                case "CONCATENAR NUMERO ATRAS": {
                    datoGeneral = l + variable1;
                }
            }
        }
        return datoGeneral;
    }

    public String busqueda(String variable1, String variable2, String l, String funcion) {

        String datoGeneral = "";
        String result = "";
        if (null == funcion) {
            return result = "SIN REGLA";
        } else {
            switch (funcion) {
                case "BUSQUEDA REEMPLAZAR 1": {
                    if (variable1.contains(l)) {
                        datoGeneral = l;
                        String replace = datoGeneral.replace(l, variable2);
                        result = replace;

                    } else {

                    }
                }
                case "BUSQUEDA REEMPLAZAR 2": {
                    if (variable1.contains(l)) {
                        datoGeneral = l;
                        String replace = datoGeneral.replace(l, variable2);
                        result = replace;

                    } else {

                    }
                }
                case "BUSQUEDA REEMPLAZAR 3": {
                    if (variable1.contains(l)) {
                        datoGeneral = l;
                        String replace = datoGeneral.replace(l, variable2);
                        result = replace;
                    } else {

                    }
                }
            }
        }
        return result;
    }

    public String filtrar(String varible1, String columnSearch, String l, String funcion) {
        String datoGeneral = "";

        if (null == funcion) {
            return datoGeneral = "SIN REGLA";
        } else {
            switch (funcion) {
                case "FILTRAR": {
                    if (varible1.equals(columnSearch)) {
                        datoGeneral = l;
                    } else {

                    }
                }
            }
        }
        return datoGeneral;
    }
    
    public String busquedasMultiples(String varible1, String columnSearch,  String codigo,String serial, String funcion) {
        String datoGeneral = "";

        if (null == funcion) {
            return datoGeneral = "SIN REGLA";
        } else {
            switch (funcion) {
                case "BUSQUEDA CODIGOS 1": {
                    if (codigo.equals(columnSearch)) {
                        datoGeneral = varible1 + serial;
                    }else{
                    }
                }
                case "BUSQUEDA CODIGOS 2": {
                    if (codigo.equals(columnSearch)) {
                        datoGeneral = varible1 + serial;
                    }else{
                    }
                }
                case "BUSQUEDA CODIGOS 3": {
                    if (codigo.equals(columnSearch)) {
                        datoGeneral = varible1 + serial;
                
                    }else{
                    }
                }
                case "BUSQUEDA CODIGOS 4": {
                    if (codigo.equals(columnSearch)) {
                        String serie = serial;
                        datoGeneral = varible1 + serie;
                        System.out.println(datoGeneral);
                    }else{
                    }
                }
                case "BUSQUEDA CODIGOS 5": {
                    if (codigo.equals(columnSearch)) {
                        datoGeneral = varible1 + serial;
                    }else{
                    }
                }
                case "BUSQUEDA CODIGOS 6": {
                    if (codigo.equals(columnSearch)) {
                        datoGeneral = varible1 + serial;
                    }else{
                    }
                }
                case "BUSQUEDA CODIGOS 7": {
                    if (codigo.equals(columnSearch)) {
                        datoGeneral = varible1 + serial;
                    }else{
                    }
                }
                case "BUSQUEDA CODIGOS 8": {
                    if (codigo.equals(columnSearch)) {
                        datoGeneral = varible1 + serial;
                    }else{
                    }
                }
                case "BUSQUEDA CODIGOS 9": {
                    if (codigo.equals(columnSearch)) {
                        datoGeneral = varible1 + serial;
                    }else{
                    }
                }
                case "BUSQUEDA CODIGOS 10": {
                    if (codigo.equals(columnSearch)) {
                        datoGeneral = varible1 + serial;
                    }else{
                    }
                }
                case "BUSQUEDA CODIGOS 11": {
                    if (codigo.equals(columnSearch)) {
                        datoGeneral = varible1 + serial;
                    }else{
                    }
                }
                case "BUSQUEDA CODIGOS EXTRAER": {
                    datoGeneral = serial;

                }

            }
        }
        return datoGeneral;
    }

}
