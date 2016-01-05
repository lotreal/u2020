package com.jakewharton.u2020.ui;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public final class UiModule {
  @Provides @Singleton AppContainer provideAppContainer() {
    return AppContainer.DEFAULT;
  }

  @Provides @Singleton ActivityHierarchyServer provideActivityHierarchyServer() {
    return ActivityHierarchyServer.NONE;
  }
}
