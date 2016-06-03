package com.byronginvest.modulecollections.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.byronginvest.modulecollections.R;
import com.byronginvest.modulecollections.data.mvp.bean.IPInfo;
import com.byronginvest.modulecollections.data.mvp.presenter.IPPresenter;
import com.byronginvest.modulecollections.data.mvp.view.IPView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gosha on 2016-06-02.
 */
public class MVPSampleActivity extends AppCompatActivity implements IPView {
    @BindView(R.id.text_ip)
    TextView textIp;
    @BindView(R.id.btn_getIp)
    Button btnGetIp;

    private IPPresenter ipPresenter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_mvp_re);
        ButterKnife.bind(this);

        ipPresenter = new IPPresenter(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_getIp)
    public void onClick() {
        Map params = new HashMap<String, String>();
        params.put("ip", "63.223.108.42");
        ipPresenter.getIpInfo("63.223.108.42");
    }

    @Override
    public void getIpInfo(Map<String, String> params) {

    }

    @Override
    public void SetData(IPInfo ipInfo) {
        textIp.setText("国家: " + ipInfo.getCountry() + " IP" + ipInfo.getIp());
    }

    @Override
    public void onError(String errMsg) {
        Toast.makeText(context, "Error: " + errMsg, Toast.LENGTH_SHORT).show();
    }

}
