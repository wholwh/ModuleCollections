package com.byronginvest.modulecollections.data.mvp.model;

import android.util.Log;

import com.byronginvest.modulecollections.data.iservice.IFIPService;
import com.byronginvest.modulecollections.data.mvp.bean.IPInfo;
import com.byronginvest.modulecollections.data.response.IPInfoResponse;
import com.squareup.okhttp.Request;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Gosha on 2016-06-02.
 */
public class IPModel {
    public Observable<IPInfo> getIpInfo(final String ip) {
        return Observable.create(new Observable.OnSubscribe<IPInfo>() {
            @Override
            public void call(final Subscriber<? super IPInfo> subscriber) {
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .readTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .build();


                Retrofit retrofit = new Retrofit.Builder()
                        .client(okHttpClient)
                        .baseUrl("http://ip.taobao.com")
                        .addConverterFactory(JacksonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

                IFIPService ifipService = retrofit.create(IFIPService.class);
                ifipService.getIpInfo(ip)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<IPInfoResponse>() {
                            @Override
                            public void onCompleted() {
                                Log.d("IP", "OnCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("IP", e.getMessage());
                                subscriber.onError(new Exception("获取对象失败"));
                            }

                            @Override
                            public void onNext(IPInfoResponse ipInfoResponse) {
                                subscriber.onNext(ipInfoResponse.data);
                                subscriber.onCompleted();
                            }
                        });
            }
        });
    }
}


class SignRequestUtil {
    public static Request signRequest(Request originalRequest) {
        Request.Builder requestBuilder = originalRequest.newBuilder().addHeader("text", "utf_8");

        // 在這做所有你需要做的事情，重新產生一個 Request 送出去。
        return requestBuilder
                .headers(requestBuilder.build().headers())
                .build();
    }
}
