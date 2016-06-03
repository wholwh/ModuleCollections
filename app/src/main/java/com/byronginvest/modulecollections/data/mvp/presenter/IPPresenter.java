package com.byronginvest.modulecollections.data.mvp.presenter;

import android.util.Log;

import com.byronginvest.modulecollections.data.mvp.bean.IPInfo;
import com.byronginvest.modulecollections.data.mvp.model.IPModel;
import com.byronginvest.modulecollections.data.mvp.view.IPView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Gosha on 2016-06-02.
 */
public class IPPresenter {
    private IPModel ipModel;
    private IPView ipView;

    public IPPresenter(IPView ipView) {
        this.ipView = ipView;
        ipModel = new IPModel();
    }

    public void getIpInfo(String ip) {
        ipModel.getIpInfo(ip)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IPInfo>() {
                    @Override
                    public void onCompleted() {
                        Log.e("IP", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("IP", "onError: " + e.getMessage());
                        ipView.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(IPInfo ipInfo) {
                        ipView.SetData(ipInfo);
                    }
                });

    }
}
