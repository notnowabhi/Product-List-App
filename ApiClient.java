package com.example.newappproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ApiClient {

    private static final String BASE_URL = "https://dummyjson.com/";
    private static ApiClient instance;
    private ApiService apiService;

    private ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public void getProductsFromApi(ApiCallback<List<Product>> callback) {
        apiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to fetch products");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
