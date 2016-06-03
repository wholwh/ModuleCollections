package com.byronginvest.modulecollections.data.iservice;

import com.byronginvest.modulecollections.data.response.IPInfoResponse;

import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Gosha on 2016-06-02.
 */
public interface IFIPService {
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @POST("service/getIpInfo.php")
    Observable<IPInfoResponse> getIpInfo(@Query("ip") String ip);
}
