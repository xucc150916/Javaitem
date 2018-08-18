package com.xucc.weixin_sell.controller;

import com.google.gson.Gson;
import com.xucc.weixin_sell.vo.AccessTokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 用于微信api获取的控制层
 * @author xuche
 * @RestController 返回以json格式
 *
 *
 */
@RestController
@RequestMapping("/wechat")
public class WeixinController {

    @Autowired
    Gson gson;
    /**
     * 微信自动回调该方法，并传入code字段，开发者拿到code再传给微信，获取到accessToken
     * @param code 微信回调时传回的code字段，用于获取access_token字段
     */
    @RequestMapping("/accessToken")
    public String accessTokenTest(@RequestParam("code") String code) {
        System.out.println("进入access的test方法");

        // 使用获取到的code向微信换取access_token的url，这里的appid与secret输入测试号的appid与secret
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=wxf0ece624168c92d0" +
                "&secret=081c5f984c6b871a9d82f08f13757348" +
                "&code=" +
                code +
                "&grant_type=authorization_code";
        RestTemplate request = new RestTemplate();
        // 向url发送get请求，返回json形式的code
        String response = request.getForObject(url, String.class);

        // 将json字符串内容映射到实体类，即解析json
        AccessTokenVO accessTokenVO = gson.fromJson(response, AccessTokenVO.class);

        System.out.println("从微信返回的信息为："+accessTokenVO);
        return response;
    }

    @RequestMapping("/hello")
    public void test() {
        System.out.println("hello world");
    }
}
