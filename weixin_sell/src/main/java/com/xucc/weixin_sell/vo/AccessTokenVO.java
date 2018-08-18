package com.xucc.weixin_sell.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * AccessToken实体类
 */
@Data
public class AccessTokenVO {

    // 使用该注解将该字段映射到json的字段
    @SerializedName(value = "access_token")
    private String accessToken;

    @SerializedName(value = "openid")
    private String openId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "AccessTokenVO{" + "\n" +
                "accessToken = '" + accessToken + '\'' + ",\n" +
                "openId = '" + openId + '\'' + "\n" +
                '}';
    }
}
