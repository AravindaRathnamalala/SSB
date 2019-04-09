package com.epic.ssb.injection.module;

import android.app.Application;
import android.content.Context;

//import com.epic.ssb.data.DatabaseControl;
import com.epic.ssb.injection.ApplicationContext;
//import com.epic.ssb.network.ApiService;
//import com.epic.ssb.util.AppPref;
import com.epic.ssb.injection.ApplicationContext;
import com.epic.ssb.network.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {

  protected final Application mApplication;

  public ApplicationModule(Application application) {
    mApplication = application;
  }

  @Provides
  Application provideApplication() {
    return mApplication;
  }

  @Provides
  @ApplicationContext
  Context provideContext() {
    return mApplication;
  }

  @Provides
  @Singleton
  ApiService provideApiService() {
    return ApiService.Creator.newApiService();
  }

//  @Provides
//  @Singleton DatabaseControl provideDatabase(){return new DatabaseControl(mApplication);}
//
//  @Provides
//  @Singleton
//  AppPref providePreferenceMangaer(){return new AppPref(mApplication);}
}
