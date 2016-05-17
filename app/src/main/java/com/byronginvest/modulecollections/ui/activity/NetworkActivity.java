package com.byronginvest.modulecollections.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.byronginvest.modulecollections.R;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Gosha on 2016-05-16.
 */
public class NetworkActivity extends AppCompatActivity {
    private Context context = this;
    private String url = "http://www.juhengdian.com/APP/getScenic.ashx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        RequestPostZipData();
    }

    private void RequestPostZipData() {
        JsonObject json = new JsonObject();
        json.addProperty("foo", "bar");
        Ion.with(context)
                .load("http://www.juhengdian.com/APP/getScenic.ashx")
                .setJsonObjectBody(json)
                .asByteArray()
                .setCallback(new FutureCallback<byte[]>() {
                    @Override
                    public void onCompleted(Exception e, byte[] result) {
                        Log.e("Network3", result.toString());
                    }
                });

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("appkey", "CB3A42B9559762B00127AF7ECEA4079C");
            jsonObject.put("platform", "android-4.3.1");
            jsonObject.put("appversion", "2.2.1");
            jsonObject.put("signa", "FF45DFB530E56A3149B65942CA56DA77");
            jsonObject.put("ts", "1463465198");
            jsonObject.put("nid", "notice");
            jsonObject.put("page", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        FormBody body = new FormBody.Builder()
                .add("appkey", "CB3A42B9559762B00127AF7ECEA4079C")
                .add("platform", "android-4.3.1")
                .add("appversion", "2.2.1")
                .add("signa", "FF45DFB530E56A3149B65942CA56DA77")
                .add("ts", "1463465198")
                .add("nid", "notice")
                .add("page", "1").build();
//        https://www.byronginvest.com/app/notice/list.html
        Request request = new Request.Builder()
                .url("http://www.juhengdian.com/APP/getScenic.ashx")
                .header("Content-Type", "text/plain")
                .header("Content-Encoding", "gzip")
                .post(body)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("Network1", response.body().string());

            }
        });


        AsyncHttpClient client = new AsyncHttpClient();
        client.post(context, "http://www.juhengdian.com/APP/getScenic.ashx", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("Network2", responseBody + "");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, url
                , new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Network4", response + "");
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("params1", "value1");
//                map.put("params2", "value2");
                return map;
            }
        };

        requestQueue.add(stringRequest);
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
