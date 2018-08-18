package com.xucc.weixin_sell.common;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Json的bean
 */
@Component
public class Common {

    @Bean
    public Gson getGson() {
        return new Gson();
    }

}
