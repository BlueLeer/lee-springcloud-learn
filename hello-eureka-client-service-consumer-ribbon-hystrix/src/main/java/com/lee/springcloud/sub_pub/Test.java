package com.lee.springcloud.sub_pub;

import rx.Observable;
import rx.Subscriber;

/**
 * @author lee
 * @date 2020/4/15 11:13
 */
public class Test {
    public static void main(String[] args) {
        // 创建被观察者(也叫事件源)
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Lee");
                subscriber.onCompleted();
            }
        });

        // 创建订阅者
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        // 被观察者指定订阅者
        observable.subscribe(subscriber);
    }
}
