package com.epic.ssb.injection.component;

import android.app.Application;
import android.content.Context;

//import com.epic.ssb.data.DatabaseControl;
import com.epic.ssb.injection.ApplicationContext;
import com.epic.ssb.injection.module.ApplicationModule;
import com.epic.ssb.network.ApiService;
//import com.epic.ssb.network.ApiService;
//import com.epic.ssb.remote.LoginSync;
//import com.epic.ssb.remote.RegisterSync;
//import com.epic.ssb.util.AppPref;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponents {
  @ApplicationContext
  Context context();

//  LoginSync loginSync();
//
//  RegisterSync registerSync();

  Application application();

//  AppPref preferencesManager();
//
//  DatabaseControl databaseControl();
//
  ApiService apiService();
}
