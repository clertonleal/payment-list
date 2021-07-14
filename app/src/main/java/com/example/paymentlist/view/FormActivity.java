package com.example.paymentlist.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.paymentlist.adapter.FormAdapter;
import com.example.paymentlist.databinding.ActivityFormBinding;
import com.example.paymentlist.model.ApplicableNetwork;
import com.example.paymentlist.network.DoPaymentNetwork;
import com.example.paymentlist.router.PaymentRouter;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFormBinding activityFormBinding = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(activityFormBinding.getRoot());
        ApplicableNetwork network = new Gson().fromJson(getIntent().getStringExtra(PaymentRouter.NETWORK), ApplicableNetwork.class);
        final FormAdapter adapter = new FormAdapter(network.getInputElements());
        activityFormBinding.listForm.setAdapter(adapter);
        activityFormBinding.button.setOnClickListener((View.OnClickListener) v -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(network.getLinks().get("operation").toString() + "/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            retrofit.create(DoPaymentNetwork.class).buyRequest(adapter.getInput());
        });


    }
}