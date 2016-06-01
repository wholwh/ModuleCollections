package com.byronginvest.modulecollections.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.byronginvest.modulecollections.R;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gosha on 2016-05-30.
 */
public class ImageLoadActivity extends AppCompatActivity {
    @BindView(R.id.il_image_picasso)
    ImageView ilImagePicasso;
    @BindView(R.id.il_image_fresco)
    SimpleDraweeView ilImageFresco;
    @BindView(R.id.il_text_name)
    TextView ilTextName;
    @BindView(R.id.il_button_start)
    Button ilButtonStart;


    private Context context =this;
    private Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_imageload);
        ButterKnife.bind(this);

        initLoadImage();
    }

    private void initLoadImage() {
        picasso = new Picasso.Builder(context)
                .downloader(new OkHttpDownloader(context))
                .memoryCache(new LruCache(context))
                .defaultBitmapConfig(Bitmap.Config.ARGB_8888)
                .build();
        picasso.with(context).load(Uri.parse("http://imgsrc.baidu.com/forum/w%3D580/sign=ed9ce8360ff431adbcd243317b37ac0f/69c835dda3cc7cd9d23b397d3a01213fb90e91e3.jpg"))
                .into(ilImagePicasso);


        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300)
                .setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP)
                .build();
        ilImageFresco.setHierarchy(hierarchy);
        ilImageFresco.setImageURI(Uri.parse("http://imgsrc.baidu.com/forum/w%3D580/sign=ed9ce8360ff431adbcd243317b37ac0f/69c835dda3cc7cd9d23b397d3a01213fb90e91e3.jpg"));
    }

    @OnClick(R.id.il_button_start)
    public void onClick() {
        Toast.makeText(context, "Button is onClick", Toast.LENGTH_SHORT).show();
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
