package com.example.paymentlist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paymentlist.R;
import com.example.paymentlist.model.ApplicableNetwork;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentViewHolder> {

    private final List<ApplicableNetwork> networks;

    public PaymentAdapter(List<ApplicableNetwork> networks) {
        this.networks = networks;
    }

    @NonNull
    @NotNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_payment, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PaymentViewHolder holder, int position) {
        holder.bind(networks.get(position));
    }

    @Override
    public int getItemCount() {
        return networks.size();
    }
}