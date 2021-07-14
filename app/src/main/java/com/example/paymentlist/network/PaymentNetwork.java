package com.example.paymentlist.network;

import com.example.paymentlist.model.ListResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PaymentNetwork {

    @GET("shared-test/lists/listresult.json")
    Call<ListResult> getPayments();

}
