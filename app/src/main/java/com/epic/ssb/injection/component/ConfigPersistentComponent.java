package com.epic.ssb.injection.component;

import com.epic.ssb.injection.ConfigPersistent;
import com.epic.ssb.injection.module.ActivityModule;
import com.epic.ssb.injection.ConfigPersistent;
import com.epic.ssb.injection.module.ActivityModule;

import dagger.Component;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link BaseActivity} to see how this components
 * survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = {ApplicationComponents.class})
public interface ConfigPersistentComponent {
  ActivityComponent activityComponent(ActivityModule activityModuBoatInspectionsListSyncle);
}