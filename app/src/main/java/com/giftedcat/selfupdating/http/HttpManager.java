package com.giftedcat.selfupdating.http;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class HttpManager {

    /**
     * 模拟检查更新接口
     * */
    public static Observable<Object> checkVersion(){
        return Observable.just(new Object()).delay(1, TimeUnit.SECONDS);
    }

}
