package com.byronginvest.modulecollections.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.byronginvest.modulecollections.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gosha on 2016-02-17.
 */
public class AnimationActivity extends AppCompatActivity {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.image_fresco)
    SimpleDraweeView imageFresco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        loadImage();
    }

    private void loadImage() {
//        Ion.with(this)
//                .load("http://imgsrc.baidu.com/forum/w%3D580/sign=ed9ce8360ff431adbcd243317b37ac0f/69c835dda3cc7cd9d23b397d3a01213fb90e91e3.jpg")
//                .withBitmap()
//                .fadeIn(true)
//                .intoImageView(image);

        Uri uri = Uri.parse("http://imgsrc.baidu.com/forum/w%3D580/sign=ed9ce8360ff431adbcd243317b37ac0f/69c835dda3cc7cd9d23b397d3a01213fb90e91e3.jpg");
        imageFresco.setImageURI(uri);
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