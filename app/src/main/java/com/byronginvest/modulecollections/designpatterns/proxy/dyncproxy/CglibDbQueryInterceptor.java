package com.byronginvest.modulecollections.designpatterns.proxy.dyncproxy;

import com.byronginvest.modulecollections.designpatterns.proxy.DBQuery;
import com.byronginvest.modulecollections.designpatterns.proxy.IDBQuery;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Gosha on 2016-04-21.
 */
public class CglibDbQueryInterceptor implements MethodInterceptor {
    IDBQuery idbQuery = null;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (null == idbQuery) {
            idbQuery = new DBQuery();
        }
        return idbQuery.request();
    }

    public static IDBQuery createCglibProxy() {
        Enhancer enhancer = new Enhancer();
        //指定切入器,定义代理类逻辑
        enhancer.setCallback(new CglibDbQueryInterceptor());
        //指定实现入口
        enhancer.setInterfaces(new Class[]{IDBQuery.class});
        //生成代理类实例
        IDBQuery cglibProxy = (IDBQuery) enhancer.create();
        return cglibProxy;
    }
}
