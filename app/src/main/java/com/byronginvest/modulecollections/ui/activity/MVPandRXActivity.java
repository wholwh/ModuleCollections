package com.byronginvest.modulecollections.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.byronginvest.modulecollections.R;
import com.byronginvest.modulecollections.data.bean.User;
import com.byronginvest.modulecollections.ui.presenter.UserPresenter;
import com.byronginvest.modulecollections.ui.view.UserView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gosha on 2016-05-24.
 */
public class MVPandRXActivity extends AppCompatActivity implements UserView {
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.btn_updatename)
    Button btnUpdatename;


    private ProgressDialog progressDialog;
    private UserPresenter userPresenter;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_mvp_rx);
        ButterKnife.bind(this);

        userPresenter = new UserPresenter(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在加载，请稍后..");

        setListener();
//        test();
    }

//    private void test() {
//        Example example = Parcels.unwrap(getIntent().getParcelableExtra("obj"));
//        Log.e("Obj",example.getName());
//        Toast.makeText(context, example.getName(), Toast.LENGTH_SHORT).show();
//    }

    private void setListener() {
        btnUpdatename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPresenter.getUser();
            }
        });
    }

    @Override
    public void UpdateView(User user) {
        if (null == user) {
            return;
        }
        tvUsername.setText(user.getUserName());
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            case R.id.action_settings:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
