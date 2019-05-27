package com.fynnjason.sanews.utils;

/**
 * Created by FynnJason.
 * Function：处理重复点击
 */
public class ClickUtils {
    private static long mLastClickTime = 0;
    private static final int TIME_INTERVAL = 1000;

    public static boolean canClick() {
        if (System.currentTimeMillis() - mLastClickTime >= TIME_INTERVAL || System.currentTimeMillis() - mLastClickTime < 0) {
            mLastClickTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
