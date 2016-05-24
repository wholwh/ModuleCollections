package com.byronginvest.modulecollections.ui.presenter;

import com.byronginvest.modulecollections.data.model.UserModel;
import com.byronginvest.modulecollections.ui.view.UserView;

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
}
