package com.epic.ssb.remote;

import com.epic.ssb.data.ResponseBean;

public interface ArakshawaSyncIn {

    interface onArakshawaListner{

        void onArakshawaFailed(String msg);

        void onArakshawaSuccess(ResponseBean responseBean);
    }
}
