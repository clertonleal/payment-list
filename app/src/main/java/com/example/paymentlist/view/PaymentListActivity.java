package com.example.paymentlist.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.paymentlist.R;
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

    private ActivityPaymentListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationIcon(R.drawable.arrow_left);
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        PaymentListViewModel viewModel = new PaymentListViewModel(service);

        viewModel.getPaymentListObserver().observe(this,
                listResult -> binding.list.setAdapter(new PaymentAdapter(listResult.getNetworks().getApplicable()))
        );

        viewModel.getErrorMessageObserver().observe(this,
                errorMessage -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(errorMessage);
                    builder.setPositiveButton(R.string.retry, (dialog, id) -> viewModel.loadPayments());
                    builder.create().show();
                }
        );

        viewModel.getProgressObserver().observe(this,
                aBoolean -> binding.progressBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE)
        );

        getLifecycle().addObserver(viewModel);
    }
}