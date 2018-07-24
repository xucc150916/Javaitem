package com.xucc.spring3.spel;

public class SystemEnvironmentBean {

    private String path;
    private String systemDriver;
    private String appData;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSystemDriver() {
        return systemDriver;
    }

    public void setSystemDriver(String systemDriver) {
        this.systemDriver = systemDriver;
    }

    public String getAppData() {
        return appData;
    }

    public void setAppData(String appData) {
        this.appData = appData;
    }

    @Override
    public String toString() {
        return "SystemEnvironmentBean{" +
                "path='" + path + '\'' +
                ", systemDriver='" + systemDriver + '\'' +
                ", appData='" + appData + '\'' +
                '}';
    }
}
