package com.vb.testtask.data.network;

import com.vb.testtask.data.network.models.ItemTest;
import com.vb.testtask.data.network.models.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TestApi {

    @GET("api/v1/trending")
    Call<List<Test>> getTest();

    @GET("api/v1/object/{id}")
    Call<ItemTest> getItemTest(@Path("id") int id);
}
