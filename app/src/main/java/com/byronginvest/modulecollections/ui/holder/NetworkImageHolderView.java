package com.byronginvest.modulecollections.ui.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.byronginvest.modulecollections.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class NetworkImageHolderView implements Holder<String> {
    private ImageView imageView;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context,int position, String data) {
        DisplayImageOptions Options = new DisplayImageOptions.Builder().bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(100))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build();
        imageView.setImageResource(R.drawable.tp);
        ImageLoader.getInstance().displayImage(data,imageView,Options);
    }
}
