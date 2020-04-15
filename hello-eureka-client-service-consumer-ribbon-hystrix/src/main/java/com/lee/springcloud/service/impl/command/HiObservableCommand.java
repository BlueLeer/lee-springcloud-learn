package com.lee.springcloud.service.impl.command;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;

/**
 * @author lee
 * @date 2020/4/15 17:27
 */
public class HiObservableCommand extends HystrixObservableCommand<String> {
    public HiObservableCommand() {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Lee-Group")));
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("Hello");
                    subscriber.onNext("Lee");
                    subscriber.onCompleted();
                }
            }
        });
    }
}
