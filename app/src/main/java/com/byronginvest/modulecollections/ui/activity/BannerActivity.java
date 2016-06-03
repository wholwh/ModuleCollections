package com.byronginvest.modulecollections.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.byronginvest.modulecollections.R;
import com.byronginvest.modulecollections.data.util.ScreenUtil;
import com.byronginvest.modulecollections.ui.holder.NetworkImageHolderView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gosha on 2016-06-02.
 */
public class BannerActivity extends AppCompatActivity {
    @BindView(R.id.banner1)
    ConvenientBanner banner1;
//    @BindView(R.id.banner2)
//    ConvenientBanner banner2;


    private Context context = this;
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);


        int width = ScreenUtil.getScreenWidth(context);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width, (int) width / 2);
        banner1.setLayoutParams(lp);
//        banner2.setLayoutParams(lp);

        banner1.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new NetworkImageHolderView();
            }
        }, Arrays.asList(images))
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
//        banner2.setPages(new CBViewHolderCreator() {
//            @Override
//            public Object createHolder() {
//                return new NetworkImageHolderView();
//            }
//        }, Arrays.asList(images));
    }

    @Override
    protected void onResume() {
        super.onResume();
        banner1.startTurning(5000);
//        banner2.startTurning(5000);
    }


    @Override
    protected void onPause() {
        super.onPause();
        banner1.stopTurning();
//        banner2.stopTurning();
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


}
