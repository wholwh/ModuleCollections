package com.byronginvest.modulecollections.data.model;

import android.os.SystemClock;

import com.byronginvest.modulecollections.data.bean.User;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Gosha on 2016-05-24.
 */
public class UserModel {
    public Observable<User> getUser() {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                // 设置个2000ms的延迟，模拟网络访问、数据库操作等等延时操作
                SystemClock.sleep(2000);

                final User user = new User("赵日天");
                if (user == null) {
                    subscriber.onError(new Exception("User = null"));
                }else{
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                }
            }
        });
    }
}
