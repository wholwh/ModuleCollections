package com.byronginvest.modulecollections.designpatterns.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gosha on 2016-04-21.
 */
public class ReportManagerFactory {
    Map<String, IReportManager> financialReportManager = new HashMap<String, IReportManager>();
    Map<String, IReportManager> employeeReportManager = new HashMap<String, IReportManager>();

    public IReportManager getFinancialReportManager(String tenanId) {
        IReportManager r = financialReportManager.get(tenanId);
        if (null == r) {
            r = new FinancialReportManager(tenanId);
            financialReportManager.put(tenanId, r);
        }
        return r;
    }


    public IReportManager getEmployeeReportManager(String tenanId){
        IReportManager r = employeeReportManager.get(tenanId);
        if (null ==r){
            r = new EmployeeReportManager(tenanId);
            employeeReportManager.put(tenanId,r);
        }

        return r;
    }
}
