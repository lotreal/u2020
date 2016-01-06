package com.jakewharton.u2020;

import android.app.Application;
import android.content.Context;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.jakewharton.u2020.data.LumberYard;
import com.jakewharton.u2020.ui.ActivityHierarchyServer;
import com.squareup.leakcanary.LeakCanary;
import javax.inject.Inject;
import timber.log.Timber;

import static timber.log.Timber.DebugTree;

public final class U2020App extends Application {
  private U2020Component component;

  @Inject ActivityHierarchyServer activityHierarchyServer;
  @Inject LumberYard lumberYard;

  @Override public void onCreate() {
    super.onCreate();
    AndroidThreeTen.init(this);
    LeakCanary.install(this);

    if (BuildConfig.DEBUG) {
      Timber.plant(new DebugTree());
    } else {
      // TODO Crashlytics.start(this);
      // TODO Timber.plant(new CrashlyticsTree());
    }

    component = DaggerU2020Component.builder()
      .u2020Module(new U2020Module(this))
      .build();

    component.inject(this);

    lumberYard.cleanUp();
    Timber.plant(lumberYard.tree());

    registerActivityLifecycleCallbacks(activityHierarchyServer);
  }

  public static U2020App get(Context context) {
    return (U2020App)context.getApplicationContext();
  }

  public U2020Component component(){
    return component;
  }
}
