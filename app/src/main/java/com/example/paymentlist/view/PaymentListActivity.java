package com.example.paymentlist.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paymentlist.adapter.PaymentAdapter;
import com.example.paymentlist.databinding.ActivityPaymentListBinding;
import com.example.paymentlist.network.PaymentNetwork;
import com.example.paymentlist.viewmodel.PaymentListViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PaymentListActivity extends AppCompatActivity {

    @Inject
    PaymentNetwork service;

    private ActivityPaymentListBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityPaymentListBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        PaymentListViewModel viewModel = new PaymentListViewModel(service);

        viewModel.getPaymentListObserver().observe(this,
                listResult -> activityMainBinding.list.setAdapter(new PaymentAdapter(listResult.getNetworks().getApplicable()))
        );
        viewModel.getErrorMessageObserver().observe(this,
                errorMessage -> Toast.makeText(PaymentListActivity.this, errorMessage, Toast.LENGTH_LONG).show()
        );

        getLifecycle().addObserver(viewModel);
    }
}