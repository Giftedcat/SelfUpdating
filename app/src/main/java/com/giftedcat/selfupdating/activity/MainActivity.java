package com.giftedcat.selfupdating.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.giftedcat.downloadappdialog.utils.DeviceUtil;
import com.giftedcat.downloadappdialog.view.DownLoadAppDialog;
import com.giftedcat.selfupdating.R;
import com.giftedcat.selfupdating.http.HttpManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    Unbinder unbinder;
    DownLoadAppDialog downLoadAppDialog;
    private List<String> apkUrls;
    @BindView(R.id.txt_version)
    TextView txt_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        apkUrls = new ArrayList<>();
        apkUrls.add("http://files.jiabaotu.com/images/data/other/201912/php4roGFb1576826236583.apk");
        downLoadAppDialog = new DownLoadAppDialog(mContext);
        txt_version.setText("当前版本号：V" + DeviceUtil.getVersionCode(MainActivity.this));
    }

    @OnClick({R.id.btn_check_version})
    public void OnCLickView(View view) {
        switch (view.getId()) {
            case R.id.btn_check_version:
                //检查更新
                checkVersion();
                break;
        }
    }

    /**
     * 检查更新
     * */
    private void checkVersion(){
        addSubscribe(HttpManager.checkVersion()
                .observeOn(AndroidSchedulers.mainThread()) //线程调度
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog("正在请求...");
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //检查更新接口回调成功
                        downLoadAppDialog.show(apkUrls);
                        dismissDialog();
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }
}
