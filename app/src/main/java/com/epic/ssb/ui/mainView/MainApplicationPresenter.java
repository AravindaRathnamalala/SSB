package com.epic.ssb.ui.mainView;

import com.epic.ssb.data.ArakshawaModel;
import com.epic.ssb.data.ResponseBean;
import com.epic.ssb.data.SurekumaModel;
import com.epic.ssb.remote.ArakshawaSync;
import com.epic.ssb.remote.ArakshawaSyncIn;
import com.epic.ssb.remote.SurekumaSync;
import com.epic.ssb.remote.SurekumaSyncIn;
import com.epic.ssb.ui.base.BasePresenter;

import javax.inject.Inject;

public class MainApplicationPresenter extends BasePresenter<MainApplicationView> {

    private ArakshawaSync arakshawaSync;
    private SurekumaSync surekumaSync;

    @Inject
    public MainApplicationPresenter(ArakshawaSync arakshawaSync, SurekumaSync surekumaSync) {
        this.arakshawaSync = arakshawaSync;
        this.surekumaSync = surekumaSync;
    }

    public void clientRegistrationArakshawa(ArakshawaModel arakshawaModel) {

        getView().showProgressDialog();

        arakshawaSync.registerArakshawa(arakshawaModel, new ArakshawaSyncIn.onArakshawaListner() {

            @Override
            public void onArakshawaFailed(String msg) {
                getView().hideProgressDialog();
                getView().showErrorMessage(msg);
            }

            @Override
            public void onArakshawaSuccess(ResponseBean responseBean) {
                getView().hideProgressDialog();
                getView().registerSuccessful(responseBean);
            }
        });
    }

    public void clientRegistrationSurekuma(SurekumaModel surekumaModel) {

        getView().showProgressDialog();

        surekumaSync.registerSurekuma(surekumaModel, new SurekumaSyncIn.onSurekumaListner() {

            @Override
            public void onSurekumaFailed(String msg) {
                getView().hideProgressDialog();
                getView().showErrorMessage(msg);
            }

            @Override
            public void onSurekumaSuccess(ResponseBean responseBean) {
                getView().hideProgressDialog();
                getView().registerSuccessful(responseBean);
            }
        });
    }
}
