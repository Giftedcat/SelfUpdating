package com.giftedcat.downloadappdialog.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;

import com.android.volley.MainUtils.HttpTools;
import com.android.volley.ext.HttpCallback;
import com.giftedcat.downloadappdialog.R;
import com.giftedcat.downloadappdialog.listener.DownLoadAppListener;
import com.giftedcat.downloadappdialog.utils.DeviceUtil;
import com.giftedcat.downloadappdialog.utils.LogUtil;
import com.giftedcat.downloadappdialog.utils.UrlUtils;

import java.io.File;
import java.util.List;

public class DownLoadAppDialog extends Dialog {

    private TextView txt_dec, home_tv_appName;
    private NestedScrollView scrollView;

    String content = "";
    private Context mContext;
    private HttpTools httpTools;
    private DownLoadAppListener listener;
    /**
     * 需要下载的apk的地址
     */
    private List<String> apkUrls;

    public DownLoadAppDialog(@NonNull Context context) {
        super(context, R.style.Dialog_Fullscreen);

        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_down_app);

        httpTools = new HttpTools(mContext.getApplicationContext());
        initView();
    }

    /**
     * 获取需要下载的文件地址，显示弹窗
     */
    public void show(List<String> apkUrls) {
        show();
        content = "";
        this.apkUrls = apkUrls;
        //开始现在第一个app
        downloadApp(0);
    }

    /**
     * 设置下载成功回调
     */
    public void setOnDownLoadListener(DownLoadAppListener listener) {
        this.listener = listener;
    }



    /**
     * 轮询下载安装app的函数
     *
     * @param index 当前下载的是第几个app
     */
    private void downloadApp(final int index) {
        if (index == apkUrls.size()) {
            //index已超出范围，说明说有app已经下载完成
            setContent("\n全部下载完成!" + index + "/" + apkUrls.size() + "(3秒后关闭)");
            txt_dec.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            }, 3000);
            return;
        }
        String apkUrl = apkUrls.get(index);
        if (TextUtils.isEmpty(apkUrl)) {
            //如果apk的地址为空，则跳过，开始下载下一个
            downNextApp(index);
            return;
        }

        final String localPath = getLocalPath(apkUrl);
        httpTools.download(apkUrl, localPath, true, new HttpCallback() {
            @Override
            public void onHttpStart() {
                super.onHttpStart();

                setContent(content);
            }

            @Override
            public void onHttpLoading(long count, long current) {
                super.onHttpLoading(count, current);

                if (count <= current) {
                    //文件已下载完成
                    boolean installStatus = false;
                    if (DeviceUtil.install(localPath)) {
                        //安装成功
                        installStatus = true;
                        content += "(安装成功)";
                    } else {
                        //安装失败
                        installStatus = false;
                        content += "(安装失败)";
                    }
                    //完成后删除文件
                    deleteFile(localPath);
                    if (listener != null) {
                        //回调给使用者
                        listener.downloadFinish(installStatus, localPath);
                    }
                    setContent(content);
                } else {
                    //下载进度提示
                    setContent(content + "(" + (current * 100 / count) + "%)");
                }

            }

            @Override
            public void onHttpFinish() {
                super.onHttpFinish();
                //下载完成，开始轮询下载下一个app
                downNextApp(index);
            }
        });
    }

    /**
     * 根据下载地址创建本地文件路径
     */
    private String getLocalPath(String apkUrl) {
        //获取下载文件的文件名
        final String name = UrlUtils.getUrlFileName(apkUrl);
        content += "\n" + name;
        final String localPath = Environment.getExternalStoragePublicDirectory("") + "/Download/" + name + ".apk";
        LogUtil.i("localAppPath:" + localPath);
        //如果已有同名文件将其删除
        deleteFile(localPath);
        return localPath;
    }


    /**
     * 结束下载轮询，关闭dialog
     */
    private void downLoadFinish() {

    }

    /**
     * 下载下一个app
     */
    private void downNextApp(int index) {
        index += 1;
        downloadApp(index);
    }

    /**
     * 初始化控件函数
     */
    private void initView() {
        scrollView = findViewById(R.id.scroll);
        txt_dec = findViewById(R.id.txt_dec);
        home_tv_appName = findViewById(R.id.home_tv_appName);

        txt_dec.setTextSize(DeviceUtil.px2dip(mContext, (int) home_tv_appName.getTextSize()));

    }

    /**
     * 增加内容
     */
    private void setContent(String content) {
        txt_dec.setText(content);
        scrollToDown();
    }

    /**
     * 滚至底部
     */
    private void scrollToDown() {
        txt_dec.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        }, 0);
    }

    /**
     * 删除文件
     */
    private void deleteFile(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

}
