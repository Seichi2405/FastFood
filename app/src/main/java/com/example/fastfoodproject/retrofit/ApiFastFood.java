package com.example.fastfoodproject.retrofit;

import android.database.Observable;

import com.example.fastfoodproject.model.LoaiMonanModel;

import retrofit2.http.GET;

public interface ApiFastFood {
    @GET("getloaimonan.php")
    Observable<LoaiMonanModel> getLoaiMonan();


}
