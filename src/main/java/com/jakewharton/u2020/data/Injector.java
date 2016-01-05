package com.jakewharton.u2020.data;

import android.content.Context;

import com.jakewharton.u2020.U2020Component;

public final class Injector {
  private static final String INJECTOR_SERVICE = "com.jakewharton.u2020.injector";

  @SuppressWarnings({"ResourceType", "WrongConstant"}) // Explicitly doing a custom service.
  public static U2020Component obtain(Context context) {
    return (U2020Component) context.getSystemService(INJECTOR_SERVICE);
  }

  public static boolean matchesService(String name) {
    return INJECTOR_SERVICE.equals(name);
  }

  private Injector() {
    throw new AssertionError("No instances.");
  }
}
