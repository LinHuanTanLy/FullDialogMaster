package com.ly.fulldialog_master.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Ly on 2017/4/21.
 * 屏幕适配工具类
 */

public class DeviceUtils {
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     */
    public static int getHight(Activity context) {
        WindowManager wm = context.getWindowManager();
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }
}
