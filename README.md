# 前言

现在android一体机已经是越来越多了，广告机、查询一体机、会议以及教学上的应用，而这些一体机的版本迭代是一个麻烦的事情，因为不能让人一台一台去更新，人力成本较大。因此，前段时间经过我反复的调试，完成了一个自更新功能的demo，来分享一下。

# 效果图

我这里做个演示，是点击检查更新，实际使用中可以通过推送、长连接等方式来接收更新的通知

![image](https://upload-images.jianshu.io/upload_images/20395467-6d3f92f531360785.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这里使用到的是轮询下载安装应用的组件，具体的原理和使用请看我的另一篇博客

[https://www.jianshu.com/p/2651659ca469](https://www.jianshu.com/p/2651659ca469)

![image](https://upload-images.jianshu.io/upload_images/20395467-22b7cd456502d617.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

完成自更新，已经高了一个版本了

![image](https://upload-images.jianshu.io/upload_images/20395467-d8a6087af6b01136.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 原理解析

主要是通过注册app替换安装的广播，之后使用轮询下载安装的组件下载并静默安装新版本的apk，这个过程中老版本的会被顶掉，退回到桌面，但是因为已经注册了广播，所以替换安装的广播会被回调，之后在广播里启动我们的APP就完成了一次自更新。

这个时候就有人要问了

![image](https://upload-images.jianshu.io/upload_images/20395467-15efcc41d6968079.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 先上一下广播内的实现代码
```
    /**
     * app版本替换广播
     * */
    public static String UPDATE_ACTION = "android.intent.action.PACKAGE_REPLACED";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(UPDATE_ACTION)) {
            //获取被更新应用的包名
            String packageName = intent.getData().getEncodedSchemeSpecificPart();
            if (packageName.equals(BuildConfig.APPLICATION_ID)) {
                // 包名一致，重新启动APP
                Intent intentToStart = context.getPackageManager().getLaunchIntentForPackage(packageName);
                context.startActivity(intentToStart);
            }
        }
    }
```
#### 别忘了在清单文件中配置一下哦
```
        <!--静默安装后自启动-->
        <receiver android:name=".receiver.ReplaceAddRemoveBroadcastReceiver">
            <intent-filter>
                <action android:name="android.intent.action.PACKAGE_REPLACED" />

                <data android:scheme="package" />
            </intent-filter>
        </receiver>
```
喜欢的话记得给我点star哦
