package com.byronginvest.modulecollections.data.mvp.view;

import com.byronginvest.modulecollections.data.mvp.bean.IPInfo;

import java.util.Map;

/**
 * Created by Gosha on 2016-06-02.
 */
public interface IPView {
    void getIpInfo(Map<String, String> params);
    void SetData(IPInfo ipInfo);
    void onError(String errMsg);
}
