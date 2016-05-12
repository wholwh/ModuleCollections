package com.byronginvest.modulecollections;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.byronginvest.modulecollections.designpatterns.flyweight.IReportManager;
import com.byronginvest.modulecollections.designpatterns.flyweight.ReportManagerFactory;

/**
 * Created by Gosha on 2015-12-23.
 */
public class TestCase extends InstrumentationTestCase {
    public void test() throws Exception {
        System.out.print("==================");
        Log.e("CTX", "===============================");

        ReportManagerFactory rmf = new ReportManagerFactory();
        IReportManager rm = rmf.getFinancialReportManager("A");
        
        System.out.print(rm.createReport());
    }
}
