package com.example.paymentlist.network;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DoPaymentNetwork {

    @POST("/")
    Call<String> buyRequest(@Body Map<String, String> map);

}
