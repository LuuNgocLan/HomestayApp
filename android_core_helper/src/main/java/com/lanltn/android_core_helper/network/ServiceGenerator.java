package com.lanltn.android_core_helper.network;

import android.support.annotation.Nullable;
import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        return createService(serviceClass, baseUrl, null);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, HashMap<String, String> headers) {
        return createService(serviceClass, baseUrl, headers, null);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, HashMap<String, String> headers, Pair<String, String> authenticate) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        setupHttpClient(httpClient);
        addAuthenticator(httpClient, authenticate);
        addInterceptor(httpClient, headers);
        Retrofit.Builder builder = setApiBaseUrl(baseUrl);
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create(getGsonBuilder()));
        addLogInterceptor(httpClient);
        return builder.client(httpClient.build()).build().create(serviceClass);
    }

    private static void setupHttpClient(OkHttpClient.Builder httpClient) {
        httpClient.connectTimeout(NetworkConfig.connectionTimeout, TimeUnit.SECONDS);
        httpClient.readTimeout(NetworkConfig.readTimeout, TimeUnit.SECONDS);
        httpClient.writeTimeout(NetworkConfig.writeTimeout, TimeUnit.SECONDS);
        httpClient.retryOnConnectionFailure(false);
    }

    private static Gson getGsonBuilder() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    private static void addAuthenticator(OkHttpClient.Builder httpClient, final Pair<String, String> authenticate) {
        httpClient.authenticator(new Authenticator() {
            @Nullable
            @Override
            public Request authenticate(Route route, Response response) {
                return response.request().newBuilder().header(authenticate.first, authenticate.second).build();
            }
        });
    }

    private static void addInterceptor(OkHttpClient.Builder httpClient, final HashMap<String, String> headers) {
        httpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain)
                    throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();
                if (headers != null && headers.size() > 0) {
                    for (String key : headers.keySet()) {
                        if (headers.get(key) != null) {
                            requestBuilder.addHeader(key, headers.get(key));
                        }
                    }
                }
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
    }

    private static void addLogInterceptor(OkHttpClient.Builder httpClient) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);
    }

    private static Retrofit.Builder setApiBaseUrl(String baseUrl) {
        return new Retrofit.Builder().baseUrl(baseUrl);
    }
}
