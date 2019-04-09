package com.epic.ssb.ui.base;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.util.LongSparseArray;

import com.epic.ssb.BaseApplication;
import com.epic.ssb.R;
import com.epic.ssb.injection.component.ActivityComponent;
import com.epic.ssb.injection.component.ConfigPersistentComponent;
import com.epic.ssb.injection.component.DaggerConfigPersistentComponent;
import com.epic.ssb.injection.module.ActivityModule;
import com.epic.ssb.util.AppDialog;
import com.epic.ssb.util.AppToast;

import java.util.concurrent.atomic.AtomicLong;

import butterknife.BindString;

/**
 * Created by pradeep_r on 9/25/2018.
 */

public class BaseFragment extends android.support.v4.app.Fragment {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final LongSparseArray<ConfigPersistentComponent>
            sComponentsMap = new LongSparseArray<>();
    @BindString(R.string.msg_1)
    String msgLoading;
    private Context mContext;
    private ActivityComponent mActivityComponent;
    private long mActivityId;
    private AlertDialog progressDialog;

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        progressDialog = AppDialog.initProgressDialog(mContext, msgLoading);
        mActivityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();

        ConfigPersistentComponent configPersistentComponent = sComponentsMap.get(mActivityId, null);

        if (configPersistentComponent == null) {
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponents(BaseApplication.get(mContext).getComponent())
                    .build();

            sComponentsMap.put(mActivityId, configPersistentComponent);
        }
        mActivityComponent =
                configPersistentComponent.activityComponent(new ActivityModule(getActivity()));
    }

    @CallSuper
    protected void showToast(String msg) {
        AppToast.showToast(mContext, msg);
    }

    @CallSuper
    protected void showErrorMessage(String msg) {
        AppDialog.showMsgDialog(mContext, msg, null);
    }

    @CallSuper
    protected void showProgressDialog() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @CallSuper
    protected void hideProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (!getActivity().isChangingConfigurations()) {
            sComponentsMap.remove(mActivityId);
        }
        super.onDestroy();
    }

    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }
}
