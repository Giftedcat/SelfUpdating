package com.giftedcat.selfupdating.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.giftedcat.downloadappdialog.utils.LogUtil;
import com.giftedcat.selfupdating.BuildConfig;

/**
 * describe：app更新广播
 * author:徐诚聪
 * date：2019.7.12
 */
public class ReplaceAddRemoveBroadcastReceiver extends BroadcastReceiver {

    /**
     * app版本替换广播
     * */
    public static String UPDATE_ACTION = "android.intent.action.PACKAGE_REPLACED";
    /**
     * 开机广播
     * */
    public static String BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(UPDATE_ACTION) || intent.getAction().equals(BOOT_COMPLETED)) {
            //获取被更新应用的包名
            String packageName = intent.getData().getEncodedSchemeSpecificPart();
            if (packageName.equals(BuildConfig.APPLICATION_ID)) {
                // 包名一致，重新启动APP
                Intent intentToStart = context.getPackageManager().getLaunchIntentForPackage(packageName);
                context.startActivity(intentToStart);
            }
        }
    }
}