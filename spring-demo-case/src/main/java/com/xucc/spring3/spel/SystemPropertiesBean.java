package com.xucc.spring3.spel;

public class SystemPropertiesBean {
    private String classPath;
    private String javaHome;
    private String javaVersion;
    private String osName;

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getJavaHome() {
        return javaHome;
    }

    public void setJavaHome(String javaHome) {
        this.javaHome = javaHome;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    @Override
    public String toString() {
        return "SystemPropertiesBean{" +
                "classPath='" + classPath + '\'' +
                ", javaHome='" + javaHome + '\'' +
                ", javaVersion='" + javaVersion + '\'' +
                ", osName='" + osName + '\'' +
                '}';
    }
}
