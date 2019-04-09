package com.epic.ssb.ui.mainView;

import com.epic.ssb.data.ResponseBean;
import com.epic.ssb.ui.base.BaseView;

public interface MainApplicationView extends BaseView {

    void showErrorMessage(String msg);

    void showToast(String msg);

    void showProgressDialog();

    void hideProgressDialog();

    void registerSuccessful(ResponseBean responseBean);


}
