package com.example.paymentlist.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paymentlist.databinding.CellFormBinding;
import com.example.paymentlist.model.InputElement;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormAdapter extends RecyclerView.Adapter<FormHolder> {

    private List<InputElement> inputElements;

    public FormAdapter(List<InputElement> inputElements) {
        this.inputElements = inputElements;
    }

    @NonNull
    @NotNull
    @Override
    public FormHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        CellFormBinding cellFormBinding = CellFormBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FormHolder(cellFormBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FormHolder holder, int position) {
        holder.bind(inputElements.get(position));
    }

    @Override
    public int getItemCount() {
        return inputElements.size();
    }

    public Map<String, String> getInput() {
        Map<String, String> map = new HashMap<>();

        for (InputElement inputElement : inputElements) {
            map.put(inputElement.getName(), inputElement.getValue());
        }

        return map;
    }
}
