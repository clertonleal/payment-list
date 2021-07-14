package com.example.paymentlist.adapter;


import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paymentlist.databinding.CellFormBinding;
import com.example.paymentlist.model.InputElement;


public class FormHolder extends RecyclerView.ViewHolder {

    private final CellFormBinding binding;
    private InputElement inputElement;

    public FormHolder(@NonNull CellFormBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(InputElement inputElement) {
        this.inputElement = inputElement;
        binding.editText.setHint(inputElement.getName());
        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputElement.setValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
