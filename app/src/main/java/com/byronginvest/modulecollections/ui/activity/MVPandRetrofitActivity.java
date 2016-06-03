package com.byronginvest.modulecollections.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.byronginvest.modulecollections.R;
import com.byronginvest.modulecollections.data.iservice.IIPService;
import com.byronginvest.modulecollections.data.response.GetIpInfoResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Gosha on 2016-06-01.
 */
public class MVPandRetrofitActivity extends AppCompatActivity {
    @BindView(R.id.text_ip)
    TextView textIp;
    @BindView(R.id.btn_getIp)
    Button btnGetIp;


    private String localIp = "115.205.202.204";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_mvp_re);
        ButterKnife.bind(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_getIp)
    public void onClick() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = null;
                        Request originalRequest = chain.request();
                        Log.e("CallBack",originalRequest.toString());
                        Log.e("CallBack",originalRequest.headers().toString());
                        Request signedRequest = SignRequestUtil.signRequest(originalRequest);
                        response = chain.proceed(signedRequest);
                        Log.e("CallBack",response.toString());
                        Log.e("CallBack",signedRequest.headers().toString());
                        return response;
                    }
                })
                .cache(new Cache(getCacheDir(), 20 * 1024 * 1024))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://ip.taobao.com")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        IIPService iipService = retrofit.create(IIPService.class);
        iipService.getIpInfo(localIp, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetIpInfoResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        textIp.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(GetIpInfoResponse getIpInfoResponse) {
                        textIp.setText("IP: " + localIp + " 国家: " + getIpInfoResponse.data.getCountry());
                    }
                });
    }
}

class SignRequestUtil {
    public static Request signRequest(Request originalRequest) {
        Builder requestBuilder = originalRequest.newBuilder().addHeader("text","utf_8");

        // 在這做所有你需要做的事情，重新產生一個 Request 送出去。
        return requestBuilder
                .headers(requestBuilder.build().headers())
                .build();
    }
}
