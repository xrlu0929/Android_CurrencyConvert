package com.example.annaxlu.currencyconvert;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface GetDataService {

    @GET("latest?access_key=d78043c1da73bc2b33d74bbec6b67d01&symbols=USD,CNY")
    Call<RetroFixData> getAllrate();
}

