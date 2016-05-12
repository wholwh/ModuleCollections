package com.byronginvest.modulecollections.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ImageView;

import com.byronginvest.modulecollections.R;
import com.koushikdutta.ion.Ion;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Gosha on 2016-02-17.
 */
public class AnimationActivity extends AppCompatActivity {
    @Bind(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        loadImage();
    }

    private void loadImage() {
        Ion.with(this)
                .load("http://imgsrc.baidu.com/forum/w%3D580/sign=ed9ce8360ff431adbcd243317b37ac0f/69c835dda3cc7cd9d23b397d3a01213fb90e91e3.jpg")
                .withBitmap()
                .fadeIn(true)
                .intoImageView(image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
}
