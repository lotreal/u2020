package com.jakewharton.u2020;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * courtesy: https://gist.github.com/benjchristensen/04eef9ca0851f3a5d7bf
 */
public class RxBus {
  private static RxBus sBus;

  public static synchronized RxBus get() {
    if (sBus == null) {
      sBus = new RxBus();
    }
    return sBus;
  }
  //private final PublishSubject<Object> _bus = PublishSubject.create();

  // If multiple threads are going to emit events to this
  // then it must be made thread-safe like this instead
  private final Subject<Integer, Integer> _bus = new SerializedSubject<>(PublishSubject.create());

  public void send(Integer o) {
    _bus.onNext(o);
  }

  public Observable<Integer> toObserverable() {
    return _bus;
  }

  public boolean hasObservers() {
    return _bus.hasObservers();
  }
}
