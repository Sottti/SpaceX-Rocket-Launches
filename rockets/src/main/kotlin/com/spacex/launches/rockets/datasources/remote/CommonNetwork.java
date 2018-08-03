package com.spacex.launches.rockets.datasources.remote;

import android.content.Context;
import androidx.annotation.NonNull;
import com.spacex.launches.rockets.BuildConfig;
import com.squareup.moshi.Moshi;
import java.util.concurrent.Executors;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;

public class CommonNetwork {

  private static Moshi sMoshi;
  private static Retrofit sRetrofit;
  private static OkHttpClient sOkHttp;

  @NonNull
  private static OkHttpClient getOkHttp(@NonNull final Context context) {
    if (sOkHttp == null) {
      final int cacheSize = 15 * 1024 * 1024;
      final Cache cache = new Cache(context.getCacheDir(), cacheSize);
      sOkHttp =
          new OkHttpClient.Builder()
              .cache(cache)
              .addInterceptor(
                  new HttpLoggingInterceptor()
                      .setLevel(BuildConfig.DEBUG ? Level.BASIC : Level.NONE))
              .build();
    }
    return sOkHttp;
  }

  @NonNull
  public static Retrofit getRetrofit(@NonNull final Context context) {
    if (sRetrofit == null) {
      sRetrofit =
          new Retrofit.Builder()
              .client(getOkHttp(context))
              .baseUrl("https://api.spacexdata.com")
              .callbackExecutor(Executors.newSingleThreadExecutor())
              .build();
    }
    return sRetrofit;
  }

  @NonNull
  public static Moshi getMoshi() {
    if (sMoshi == null) {
      sMoshi = new Moshi.Builder().build();
    }
    return sMoshi;
  }
}
