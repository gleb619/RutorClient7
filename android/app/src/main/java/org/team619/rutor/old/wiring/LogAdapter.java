package org.team619.rutor.old.wiring;

import android.content.Context;
import android.widget.ArrayAdapter;

import org.team619.rutor.R;

import java.util.List;

public class LogAdapter extends ArrayAdapter<String> {

  public LogAdapter(Context context, List<String> logs) {
    super(context, R.layout.item_log, R.id.item_log, logs);
  }
}
