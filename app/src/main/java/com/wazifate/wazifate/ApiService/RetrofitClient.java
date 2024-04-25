package com.wazifate.wazifate.ApiService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static String API_BASE_URL = "http://192.168.1.3/wazifate/api/";
    private static Retrofit retrofit;
    private static RetrofitClient mInstance;
    private static Gson gson;
    public static Retrofit getRetrofitInstance()
    {
        if(retrofit==null)
        {
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return retrofit;
    }
    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }
    public ApiService getRestApi()
    {
        return getRetrofitInstance().create(ApiService.class);
    }
}
