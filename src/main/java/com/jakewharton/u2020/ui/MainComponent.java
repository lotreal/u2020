package com.jakewharton.u2020.ui;

import com.jakewharton.u2020.U2020Component;
import com.jakewharton.u2020.U2020Module;
import com.jakewharton.u2020.ui.trending.TrendingView;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by luotao on 16/1/5.
 */
@Singleton
@Subcomponent(
 modules = {
   MainActivityModule.class
 }
)
//@Component(
//  dependencies = U2020Component.class,
//  modules = {
//    MainActivityModule.class
//  }
//)
public interface MainComponent {
  void inject(MainActivity activity);
  void inject(TrendingView view);
}
