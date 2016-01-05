package com.jakewharton.u2020;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.jakewharton.u2020.data.Injector;
import com.jakewharton.u2020.data.LumberYard;
import com.jakewharton.u2020.ui.ActivityHierarchyServer;
import com.jakewharton.u2020.ui.MainActivity;
import com.jakewharton.u2020.ui.MainActivityModule;
import com.jakewharton.u2020.ui.MainComponent;
import com.squareup.leakcanary.LeakCanary;
import javax.inject.Inject;
import timber.log.Timber;

import static timber.log.Timber.DebugTree;

public final class U2020App extends Application {
  private U2020Component component;
  private MainComponent mainComponent;

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

  public MainComponent createMainComponent(MainActivity activity) {
    mainComponent = component.plus(new MainActivityModule(activity));
    return mainComponent;
  }

  public MainComponent getMainComponent(){
    return mainComponent;
  }


  @Override public Object getSystemService(@NonNull String name) {
    if (Injector.matchesService(name)) {
      return component;
    }
    return super.getSystemService(name);
  }
}
