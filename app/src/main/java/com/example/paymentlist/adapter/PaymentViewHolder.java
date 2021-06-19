package com.example.paymentlist.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.paymentlist.R;
import com.example.paymentlist.model.ApplicableNetwork;

import org.jetbrains.annotations.NotNull;

public class PaymentViewHolder extends RecyclerView.ViewHolder {

    private final ImageView paymentImage;
    private final TextView paymentText;

    public PaymentViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        paymentImage = itemView.findViewById(R.id.paymentImage);
        paymentText = itemView.findViewById(R.id.paymentName);
    }

    public void bind(ApplicableNetwork network) {
        Glide.with(itemView.getContext()).load(network.getLinks().get("logo")).into(paymentImage);
        paymentText.setText(network.getLabel());
    }
}
