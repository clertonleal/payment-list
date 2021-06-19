package com.example.paymentlist.network;

import com.example.paymentlist.model.ListResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PaymentNetwork {

    @GET("shared-test/lists/listresult.json")
    Call<ListResult> getPayments();

}
