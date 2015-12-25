package com.neighbor.retailer_android.common.utils;

import android.util.Log;

/**
 * Author: Bu
 * Date  : 2015/12/15
 * Time  : 15:25
 */
public class MLog {
    public static boolean isLog = false;

    public static void setIsLog(boolean isl) {
        isLog = isl;
    }

    public static void e(String tag, String msg) {
        if (isLog) {
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isLog) {
            Log.v(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isLog) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isLog) {
            Log.d(tag, msg);
        }
    }
}
