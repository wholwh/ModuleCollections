package com.byronginvest.modulecollections.designpatterns.proxy.dyncproxy;

import com.byronginvest.modulecollections.designpatterns.proxy.DBQuery;
import com.byronginvest.modulecollections.designpatterns.proxy.IDBQuery;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

/**
 * Created by Gosha on 2016-04-21.
 */
public class JavassistDynDbQueryHandler implements MethodHandler {
    IDBQuery idbQuery = null;

    @Override
    public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
        if (null == idbQuery) {
            idbQuery = new DBQuery();
        }
        return idbQuery.request();
    }


    public static IDBQuery createJavassistDynProxy() throws IllegalAccessException, InstantiationException {
        ProxyFactory proxyFactory = new ProxyFactory();
        //指定接口
        proxyFactory.setInterfaces(new Class[]{IDBQuery.class});
        Class proxyClass = proxyFactory.createClass();
        IDBQuery javassistProxy = (IDBQuery) proxyClass.newInstance();
        //设置Handler处理器
        ((ProxyObject) javassistProxy).setHandler(new JavassistDynDbQueryHandler());
        return javassistProxy;
    }
}
