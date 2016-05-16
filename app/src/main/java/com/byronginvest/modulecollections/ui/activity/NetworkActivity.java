package com.byronginvest.modulecollections.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.byronginvest.modulecollections.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Gosha on 2016-05-16.
 */
public class NetworkActivity extends AppCompatActivity {
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        RequestPostZipData();
    }

    private void RequestPostZipData() {
//        Ion.with(context)
//                .load("http://www.juhengdian.com/APP/getScenic.ashx")
//                .as(String.class)
//                .setCallback(new FutureCallback<String>() {
//                    @Override
//                    public void onCompleted(Exception e, String result) {
//                        Log.e("Network",result);
//                    }
//                });
//        RequestBody requestBody = RequestBody.create(MediaType.parse("text/html; charset=utf-8"),"a=1");
//        Request request = new Request.Builder()
//                .url("https://baidu.com")
//                .post(requestBody)
//                .build();
//        OkHttpClient okHttpClient = new OkHttpClient();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.e("Network", response.body().string());
//            }
//        });


        AsyncHttpClient client = new AsyncHttpClient();
        client.post(context, "http://www.juhengdian.com/APP/getScenic.ashx", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                try {
                Log.e("Network", responseBody + "");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
