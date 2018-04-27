package com.alkahshafqatar.sarahmohamed.alkashafqatar.dagger2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RestAPIModule {

    protected String baseUrl;

    public RestAPIModule(String mBaseUrl) {
        this.baseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    public OkHttpClient getOkHttpClient()
    {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        return client;
    }

    @Provides
    @Singleton
    public Retrofit getRetrofitObject (OkHttpClient client)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return  retrofit;
    }

}
