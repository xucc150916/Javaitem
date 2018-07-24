package com.xucc.spring3.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 扫描
@Component
public class MyDataSource {

//    @Value(value = "#{properties['jdbc.durl']}")
    @Value("${jdbc.url}")
    private String url;

//    @Value(value = "#{properties['jdbc.driverclass']}")
    @Value("${jdbc.driverClass}")
    private String driverClass;

//    @Value(value = "#{properties['jdbc.username']}")
    @Value("{jdbc.username}")
    private String username;

//    @Value(value = "#{properties['jdbc.password']}")
    @Value("jdbc.password")
    private String password;

    /**
     * getter/setter
     * @return
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MyDataSource{" +
                "url='" + url + '\'' +
                ", driverClass='" + driverClass + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
