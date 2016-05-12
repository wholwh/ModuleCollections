package com.byronginvest.modulecollections.designpatterns.proxy;

/**
 * Created by Gosha on 2016-04-21.
 */
public class DBQueryProxy implements IDBQuery {
    private DBQuery dbQuery = null;

    @Override
    public String request() {
        //在真正需要的时候，才创建真实对象，创建过程可能很慢
        if (null == dbQuery) {
            dbQuery = new DBQuery();
        }
        //在多线程环境下，这里返回一个虚假类，类似于Future模式
        return dbQuery.request();
    }
}
