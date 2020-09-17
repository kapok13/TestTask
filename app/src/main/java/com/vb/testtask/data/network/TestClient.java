package com.vb.testtask.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestClient {

    private static TestClient testClient;

    private Retrofit retrofit;

    public static TestClient getInstance(){
        if (testClient == null){
            testClient = new TestClient();
        }
        return testClient;
    }

    private TestClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://demo0040494.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public TestApi getTestApi() {
        return retrofit.create(TestApi.class);
    }
}
