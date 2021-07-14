package com.example.paymentlist.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.paymentlist.databinding.CellPaymentBinding;
import com.example.paymentlist.model.ApplicableNetwork;
import com.example.paymentlist.router.PaymentRouter;

public class PaymentViewHolder extends RecyclerView.ViewHolder {

    private final CellPaymentBinding binding;
    private final PaymentRouter paymentRouter;

    public PaymentViewHolder(@NonNull CellPaymentBinding binding, PaymentRouter paymentRouter) {
        super(binding.getRoot());
        this.binding = binding;
        this.paymentRouter = paymentRouter;
    }

    public void bind(ApplicableNetwork network) {
        Glide.with(itemView.getContext()).load(network.getLinks().get("logo")).into(binding.paymentImage);
        binding.paymentName.setText(network.getLabel());
        binding.getRoot().setOnClickListener(v -> paymentRouter.navigateToPaymentForm(network));
    }
}
