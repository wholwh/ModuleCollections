package com.byronginvest.modulecollections.ui.presenter;

import com.byronginvest.modulecollections.data.bean.User;
import com.byronginvest.modulecollections.data.model.UserModel;
import com.byronginvest.modulecollections.ui.view.UserView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Gosha on 2016-05-24.
 */
public class UserPresenter {
    private UserView userView;

    private UserModel userModel;

    public UserPresenter(UserView userView) {
        this.userView = userView;
        userModel = new UserModel();
    }

    public void getUser() {
        userView.showProgressDialog();
        userModel.getUser()
                .subscribeOn(Schedulers.io())//在非UI线程中执行getUser
                .observeOn(AndroidSchedulers.mainThread())//在UI线程中执行结果
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        userView.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        userView.hideProgressDialog();
                        userView.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        userView.UpdateView(user);
                    }
                });
    }
}
