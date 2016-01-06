package com.jakewharton.u2020.data;

import android.content.Context;

import com.jakewharton.u2020.U2020App;
import com.jakewharton.u2020.U2020Component;

import hugo.weaving.DebugLog;

public final class Injector {
  //@SuppressWarnings({"ResourceType", "WrongConstant"}) // Explicitly doing a custom service.
  @DebugLog
  public static U2020Component get(Context context) {
    return U2020App.get(context).component();
  }

  private Injector() {
    throw new AssertionError("No instances.");
  }
}
