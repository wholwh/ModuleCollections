package com.byronginvest.modulecollections.data.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by Gosha on 2016-05-30.
 */
public class PackageUtil {
    public static PackageInfo getPackageInfo(Context context){

        PackageManager pm = context.getPackageManager();

        try {

            return pm.getPackageInfo(context.getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {

            Log.e("PackageUtil",e.getLocalizedMessage());

        }

        return  new PackageInfo();

    }
}
