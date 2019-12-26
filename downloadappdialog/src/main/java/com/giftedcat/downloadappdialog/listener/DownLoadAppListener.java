package com.giftedcat.downloadappdialog.listener;

public interface DownLoadAppListener {

    /**
     * 下载成功回调
     * @param installStatus 安装是否成功
     * @param path 安装包路径
     * */
    void downloadFinish(boolean installStatus, String path);

}
