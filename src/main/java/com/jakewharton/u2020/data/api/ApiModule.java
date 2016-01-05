package com.jakewharton.u2020.data.api;

import com.jakewharton.u2020.data.api.oauth.OauthInterceptor;
import com.jakewharton.u2020.data.api.oauth.OauthService;
import com.squareup.moshi.Moshi;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import retrofit.MoshiConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

@Module
public class ApiModule {
  public static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("https://api.github.com");

  @Provides @Singleton
  Retrofit provideRetrofit(HttpUrl baseUrl, @Named("Api") OkHttpClient client, Moshi moshi) {
    return new Retrofit.Builder() //
        .client(client) //
        .baseUrl(baseUrl) //
        .addConverterFactory(MoshiConverterFactory.create(moshi)) //
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //
        .build();
  }

  static OkHttpClient createApiClient(OkHttpClient client, OauthInterceptor oauthInterceptor) {
    client = client.clone();
    client.interceptors().add(oauthInterceptor);
    return client;
  }
}
