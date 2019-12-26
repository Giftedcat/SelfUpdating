package com.giftedcat.downloadappdialog.utils;

import android.text.TextUtils;

public class UrlUtils {

    /**
     * 根据下载地址获取需要下载文件名
     */
    public static String getUrlFileName(String url) {
        if (TextUtils.isEmpty(url)) {
            //地址不能为空
            return System.currentTimeMillis() + "";
        }
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        if (fileName.indexOf(".") != -1) {
            return fileName.split("\\.")[0];
        } else {
            return fileName;
        }
    }

}
