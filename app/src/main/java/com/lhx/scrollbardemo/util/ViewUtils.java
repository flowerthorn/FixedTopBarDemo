package com.lhx.scrollbardemo.util;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by wangkang on 17/5/23.
 */

public class ViewUtils {
    public static void setText(TextView textView, Object object) {
        if (object == null) {
            textView.setText(null);
        } else if (object instanceof Integer) {
            textView.setText((Integer) object);
        } else if (object instanceof String) {
            textView.setText(object.toString());
        }
    }

    public static String getText(Context context, Object object) {
        if (object == null) {
            return null;
        } else if (object instanceof Integer) {
            return context.getString((Integer) object);
        } else {
            return object.toString();
        }
    }

    public static int getStatusBarHeight(Context context) {
        int statusbarheight = 0;
        if (statusbarheight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusbarheight = context.getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (statusbarheight == 0) {
            statusbarheight = ViewUtils.dip2px(context, 25);

        }
        return statusbarheight;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * sp2pX
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static float getTextWidth(Paint paint, String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }
        return paint.measureText(string);
    }
    public static float getTextHeight(Paint paint, String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }
        Rect bonuds = new Rect();
        paint.getTextBounds(string, 0, string.length(),bonuds);
        return bonuds.height();
    }


    public static float getTextHeight(Paint paint) {
        Rect bonuds = new Rect();
        paint.getTextBounds("Q", 0, 1, bonuds);
        return bonuds.height();
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }

}
