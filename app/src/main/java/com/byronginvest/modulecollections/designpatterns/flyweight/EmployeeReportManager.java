package com.byronginvest.modulecollections.designpatterns.flyweight;

/**
 * Created by Gosha on 2016-04-21.
 */
public class EmployeeReportManager implements IReportManager {
    protected  String tenantId = null;

    public EmployeeReportManager(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String createReport() {
        return "This is a Employee report";
    }
}
