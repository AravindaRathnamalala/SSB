package com.epic.ssb;

import android.app.Application;
import android.content.Context;

import com.epic.ssb.injection.component.ApplicationComponents;
import com.epic.ssb.injection.component.DaggerApplicationComponents;
import com.epic.ssb.injection.module.ApplicationModule;
import com.facebook.stetho.Stetho;
//import com.facebook.stetho.Stetho;


public class BaseApplication extends Application {

    ApplicationComponents mApplicationComponent;

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        if (BuildConfig.DEBUG) {
            //Fabric.with(this, new Crashlytics());
        }
    }

    public ApplicationComponents getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponents.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponents applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
