package com.epic.ssb.remote;

import com.epic.ssb.data.ResponseBean;

public interface LoginSyncIn {

    interface OnLoginListener{

        void onLoginFailed(String msg);

        void onLoginSuccess(ResponseBean responseBean);
    }
}
