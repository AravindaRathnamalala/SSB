package com.epic.ssb.injection.component;

import com.epic.ssb.injection.PerActivity;
import com.epic.ssb.injection.module.ActivityModule;
import com.epic.ssb.ui.mainView.ArakshawaFragment;
import com.epic.ssb.ui.mainView.MainApplicationActivity;
import com.epic.ssb.ui.mainView.SurekumaFragment;


import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(MainApplicationActivity mainApplicationActivity);

    void inject(ArakshawaFragment arakshawaFragment);

    void inject(SurekumaFragment surekumaFragment);
}
