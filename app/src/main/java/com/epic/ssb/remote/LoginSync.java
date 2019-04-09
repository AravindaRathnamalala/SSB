package com.epic.ssb.remote;

import android.content.Context;

import com.epic.ssb.data.ResponseBean;
import com.epic.ssb.injection.ApplicationContext;
import com.epic.ssb.network.ApiService;
import com.epic.ssb.util.ConnectionDetector;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginSync {

    private final ApiService mApiService;
    private final Context mContext;

    @Inject
    public LoginSync(ApiService mApiService,@ApplicationContext Context mContext) {
        this.mApiService = mApiService;
        this.mContext = mContext;
    }

    public void logIn(String username, String password, final LoginSyncIn.OnLoginListener listener){

        if(ConnectionDetector.getConnectionInstance(mContext).isOnline()){
            mApiService.login(username, password)
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
