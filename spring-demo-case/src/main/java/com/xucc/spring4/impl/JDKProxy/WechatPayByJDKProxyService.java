package com.xucc.spring4.impl.JDKProxy;

import com.xucc.spring4.PayService;
import com.xucc.spring4.component.LogComponent;
import com.xucc.spring4.component.SercurityComponent;
import com.xucc.spring4.component.TimeComponent;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 使用JDK动态代理
 */

public class WechatPayByJDKProxyService implements InvocationHandler {

    private final LogComponent logComponent;
    private final SercurityComponent sercurityComponent;
    private final TimeComponent timeComponent;
    private final PayService target;

    public WechatPayByJDKProxyService(LogComponent logComponent, SercurityComponent sercurityComponent, TimeComponent timeComponent, PayService target) {
        this.logComponent = logComponent;
        this.sercurityComponent = sercurityComponent;
        this.timeComponent = timeComponent;
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 1. 安全检查
        sercurityComponent.sercurity();
        // 2. 打开日志
        logComponent.log();
        // 3-a. 记录运行前时间
        long start = timeComponent.startTime();

        // pay()
        Object retVal = method.invoke(target, args);

        // 3-b. 记录运行后时间
        long end = timeComponent.endTime();
        timeComponent.useTime(start, end);

        return null;
    }
}
