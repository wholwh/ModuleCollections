package com.byronginvest.modulecollections.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.byronginvest.modulecollections.R;
import com.byronginvest.modulecollections.data.util.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by Gosha on 2016-05-12.
 */
public class UltraActivity extends AppCompatActivity {
//    @Bind(R.id.store_house_ptr_image)
//    CubeImageView storeHousePtrImage;
    @BindView(R.id.store_house_ptr_image_content)
    LinearLayout storeHousePtrImageContent;
    @BindView(R.id.store_house_ptr_frame)
    PtrFrameLayout storeHousePtrFrame;


    private Context ctx = this;
//    private StoreHouseHeader header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

//        initStoreHouseHeader();

//        View header = View.inflate(ctx,R.layout.cube_views_load_more_default_footer,null);
//        TextView tv = (TextView) header.findViewById(R.id.cube_views_load_more_default_footer_text_view);
//        tv.setText("下拉刷新");
        StoreHouseHeader header = new StoreHouseHeader(UltraActivity.this);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtil.dip2px(ctx,20), 0, DensityUtil.dip2px(ctx,10));
        header.initWithString("HELLO WORLD");
        header.setTextColor(getResources().getColor(android.R.color.holo_green_light));
        header.setBackgroundColor(getResources().getColor(android.R.color.black));


        storeHousePtrFrame.setHeaderView(header);
        storeHousePtrFrame.addPtrUIHandler(header);
        storeHousePtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        storeHousePtrFrame.refreshComplete();
                    }
                }, 2500);
            }
        });

//        storeHousePtrFrame.autoRefresh();
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

//    private void initStoreHouseHeader() {
//        // header
//        header = new StoreHouseHeader(ctx);
//        header.setPadding(0, LocalDisplay.dp2px(15), 0, 0);
//
//        /**
//         * using a string, support: A-Z 0-9 - .
//         * you can add more letters by {@link in.srain.cube.views.ptr.header.StoreHousePath#addChar}
//         */
//        header.initWithStringArray(R.array.storehouse);
//    }
}
