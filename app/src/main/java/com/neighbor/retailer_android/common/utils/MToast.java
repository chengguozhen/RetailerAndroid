package com.neighbor.retailer_android.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Author: Bu
 * Date  : 2015/12/15
 * Time  : 15:21
 */
public class MToast {
    public static Toast toast = null;

    /**
     * Toast显示
     *
     * @param text
     */
    public static void show(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
}
