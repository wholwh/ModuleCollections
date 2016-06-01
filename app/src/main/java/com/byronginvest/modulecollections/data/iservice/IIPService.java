package com.byronginvest.modulecollections.data.iservice;

import com.byronginvest.modulecollections.data.response.GetIpInfoResponse;

import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Gosha on 2016-06-01.
 */
public interface IIPService {
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @POST("service/getIpInfo.php")
    Observable<GetIpInfoResponse> getIpInfo(@Query("ip") String ip, @Query("mac") String mac);
}
