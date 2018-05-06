package org.team619.rutor.old.fragments;

import android.support.v4.app.Fragment;

import com.squareup.leakcanary.RefWatcher;

import org.team619.rutor.MyApp;

public class BaseFragment extends Fragment {

  @Override
  public void onDestroy() {
    super.onDestroy();
    RefWatcher refWatcher = MyApp.getRefWatcher();
    refWatcher.watch(this);
  }
}
