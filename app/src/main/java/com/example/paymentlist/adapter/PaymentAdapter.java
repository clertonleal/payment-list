package com.example.paymentlist.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paymentlist.databinding.CellPaymentBinding;
import com.example.paymentlist.model.ApplicableNetwork;
import com.example.paymentlist.router.PaymentRouter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentViewHolder> {

    private final List<ApplicableNetwork> networks;
    private final PaymentRouter paymentRouter;

    public PaymentAdapter(List<ApplicableNetwork> networks, PaymentRouter paymentRouter) {
        this.networks = networks;
        this.paymentRouter = paymentRouter;
    }

    @NonNull
    @NotNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        CellPaymentBinding cellPaymentBinding = CellPaymentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PaymentViewHolder(cellPaymentBinding, paymentRouter);
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
