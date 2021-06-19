package com.example.paymentlist.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.paymentlist.databinding.CellPaymentBinding;
import com.example.paymentlist.model.ApplicableNetwork;

public class PaymentViewHolder extends RecyclerView.ViewHolder {

    private final CellPaymentBinding binding;

    public PaymentViewHolder(@NonNull CellPaymentBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ApplicableNetwork network) {
        Glide.with(itemView.getContext()).load(network.getLinks().get("logo")).into(binding.paymentImage);
        binding.paymentName.setText(network.getLabel());
    }
}
