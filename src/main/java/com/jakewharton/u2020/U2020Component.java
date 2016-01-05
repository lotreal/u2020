package com.jakewharton.u2020;

import com.jakewharton.u2020.data.api.oauth.OauthService;
import com.jakewharton.u2020.ui.MainActivity;
import com.jakewharton.u2020.ui.MainActivityModule;
import com.jakewharton.u2020.ui.debug.DebugView;
import com.jakewharton.u2020.ui.trending.TrendingView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by luotao on 16/1/5.
 */
@Singleton
@Component(modules = {U2020Module.class})
public interface U2020Component {
  void inject(U2020App app);
  void inject(OauthService service);
  void inject(TrendingView view);
  void inject(DebugView view);
  void inject(MainActivity activity);
}
