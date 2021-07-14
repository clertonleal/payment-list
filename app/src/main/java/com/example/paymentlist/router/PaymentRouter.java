package com.example.paymentlist.router;

import android.content.Context;
import android.content.Intent;

import com.example.paymentlist.model.ApplicableNetwork;
import com.example.paymentlist.view.FormActivity;
import com.google.gson.Gson;

public class PaymentRouter {

    private final Context context;
    public static final String NETWORK = "network";

    public PaymentRouter(Context context) {
        this.context = context;
    }

    public void navigateToPaymentForm(ApplicableNetwork network) {
        String networkAsString = new Gson().toJson(network);
        Intent intent = new Intent(context, FormActivity.class);
        intent.putExtra(NETWORK, networkAsString);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
