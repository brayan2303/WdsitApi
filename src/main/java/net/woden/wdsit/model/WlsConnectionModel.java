package net.woden.wdsit.model;

public class WlsConnectionModel {
    private static String ip;
    private static String dataBase;
    private static String userName;
    private static String password;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        WlsConnectionModel.ip = ip;
    }

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        WlsConnectionModel.dataBase = dataBase;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        WlsConnectionModel.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        WlsConnectionModel.password = password;
    }
}
