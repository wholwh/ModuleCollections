package com.byronginvest.modulecollections.ui.view;

import com.byronginvest.modulecollections.data.bean.User;

/**
 * Created by Gosha on 2016-05-24.
 */
public interface UserView {
    void UpdateView(User user);
    void showProgressDialog();
    void hideProgressDialog();
    void onError(String msg);
}
