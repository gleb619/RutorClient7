package org.team619.rutor;

import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.team619.rutor.component.BasicComponent;
import org.team619.rutor.component.DaggerBasicComponent;
import org.team619.rutor.config.AppModule;
import org.team619.rutor.old.volley.MyVolley;

import timber.log.Timber;

public class MyApp extends MultiDexApplication {

  private static MyApp _instance;
  private RefWatcher _refWatcher;
    private BasicComponent basicComponent;

  public static MyApp get() {
    return _instance;
  }

  public static RefWatcher getRefWatcher() {
    return MyApp.get()._refWatcher;
  }

  @Override
  public void onCreate() {
    super.onCreate();

    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }

    _instance = (MyApp) getApplicationContext();
    _refWatcher = LeakCanary.install(this);

    // Initialize Volley
    MyVolley.init(this);

    Timber.plant(new Timber.DebugTree());

      basicComponent = DaggerBasicComponent.builder()
              .appModule(new AppModule(getApplicationContext()))
              .build();
  }

    public BasicComponent basicComponent() {
        return basicComponent;
    }

}
