package com.epic.ssb.remote;

import android.content.Context;

import com.epic.ssb.data.ResponseBean;
import com.epic.ssb.data.SurekumaModel;
import com.epic.ssb.injection.ApplicationContext;
import com.epic.ssb.network.ApiService;
import com.epic.ssb.util.ConnectionDetector;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SurekumaSync {

    private final Context mContext;
    private final ApiService mApiService;

    public SurekumaSync(@ApplicationContext Context mContext, ApiService mApiService) {
        this.mContext = mContext;
        this.mApiService = mApiService;
    }

    public void registerSurekuma(SurekumaModel surekumaModel, final SurekumaSyncIn.onSurekumaListner listner) {

        if (ConnectionDetector.getConnectionInstance(mContext).isOnline()) {
            mApiService.clientRegisterSurekuma(surekumaModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBean>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Response<ResponseBean> responseBeanResponse) {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }

}
