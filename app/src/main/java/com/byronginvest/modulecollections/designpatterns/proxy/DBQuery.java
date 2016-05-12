package com.byronginvest.modulecollections.designpatterns.proxy;

/**
 * Created by Gosha on 2016-04-21.
 */
public class DBQuery implements IDBQuery {

    public DBQuery() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String request() {
        return "request string";
    }
}
