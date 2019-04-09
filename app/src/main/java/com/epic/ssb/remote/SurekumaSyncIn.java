package com.epic.ssb.remote;

import com.epic.ssb.data.ResponseBean;

public interface SurekumaSyncIn {

    interface onSurekumaListner{
        void onSurekumaFailed(String msg);

        void onSurekumaSuccess(ResponseBean responseBean);
    }
}
