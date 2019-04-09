package com.epic.ssb.remote;

import android.content.Context;

import com.epic.ssb.data.AgentModel;
import com.epic.ssb.data.ResponseBean;
import com.epic.ssb.injection.ApplicationContext;
import com.epic.ssb.network.ApiService;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AgentSync {

    private final Context mContext;
    private final ApiService mApiService;

    @Inject
    public AgentSync(@ApplicationContext Context mContext, ApiService mApiService) {
        this.mContext = mContext;
        this.mApiService = mApiService;
    }

    public void registerAgent(AgentModel agentModel) {
        mApiService.agentRegister(agentModel)
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
