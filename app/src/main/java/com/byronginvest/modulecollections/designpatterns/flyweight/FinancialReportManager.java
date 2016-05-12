package com.byronginvest.modulecollections.designpatterns.flyweight;

/**
 * Created by Gosha on 2016-04-21.
 */
public class FinancialReportManager implements IReportManager {
    protected  String tenanId = null;

    public FinancialReportManager(String tenanId) {
        this.tenanId = tenanId;
    }

    @Override
    public String createReport() {
        return "This is a Financial report";
    }
}
