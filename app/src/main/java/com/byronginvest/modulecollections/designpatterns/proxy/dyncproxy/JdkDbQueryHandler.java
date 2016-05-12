package com.byronginvest.modulecollections.designpatterns.proxy.dyncproxy;

import com.byronginvest.modulecollections.designpatterns.proxy.DBQuery;
import com.byronginvest.modulecollections.designpatterns.proxy.IDBQuery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Gosha on 2016-04-21.
 */
public class JdkDbQueryHandler implements InvocationHandler {
    IDBQuery idbQuery = null;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (null == idbQuery) {
            idbQuery = new DBQuery();
        }
        return idbQuery.request();
    }

    public static IDBQuery createJdkProxy() {
        IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader()
                , new Class[]{IDBQuery.class}, new JdkDbQueryHandler());
        return jdkProxy;
    }
}
