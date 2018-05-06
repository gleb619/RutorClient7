package org.team619.rutor;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.team619.rutor.http.HttpServer;
import org.team619.rutor.old.fragments.MainFragment;
import org.team619.rutor.old.fragments.RotationPersist1WorkerFragment;
import org.team619.rutor.old.fragments.RotationPersist2WorkerFragment;
import org.team619.rutor.old.rxbus.RxBus;

import java.io.IOException;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getName();
  @Inject
  HttpServer httpServer;
  private RxBus _rxBus = null;

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    _removeWorkerFragments();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    MyApp.get().basicComponent().inject(this);
    WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
    String ip = android.text.format.Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
    Log.i(TAG, "Server running at " + ip + ":8080");

    if (savedInstanceState == null) {
      getSupportFragmentManager()
          .beginTransaction()
          .replace(android.R.id.content, new MainFragment(), this.toString())
          .commit();
    }
  }

  @Override
  protected void onStart() {
    if (httpServer == null) httpServer = new HttpServer();
    try {
      httpServer.start();
    } catch (IOException e) {
      Timber.e(e);
    }
    super.onStart();
  }

  @Override
  protected void onStop() {
    if (httpServer != null) httpServer.stop();
    super.onStop();
  }

  // This is better done with a DI Library like Dagger
  public RxBus getRxBusSingleton() {
    if (_rxBus == null) {
      _rxBus = new RxBus();
    }

    return _rxBus;
  }

  private void _removeWorkerFragments() {
    Fragment frag =
        getSupportFragmentManager()
            .findFragmentByTag(RotationPersist1WorkerFragment.class.getName());

    if (frag != null) {
      getSupportFragmentManager().beginTransaction().remove(frag).commit();
    }

    frag =
        getSupportFragmentManager()
            .findFragmentByTag(RotationPersist2WorkerFragment.class.getName());

    if (frag != null) {
      getSupportFragmentManager().beginTransaction().remove(frag).commit();
    }
  }
}
