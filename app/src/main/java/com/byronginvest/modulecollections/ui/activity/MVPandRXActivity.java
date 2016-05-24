package com.byronginvest.modulecollections.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.byronginvest.modulecollections.ui.presenter.UserPresenter;

/**
 * Created by Gosha on 2016-05-24.
 */
public class MVPandRXActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private UserPresenter userPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
