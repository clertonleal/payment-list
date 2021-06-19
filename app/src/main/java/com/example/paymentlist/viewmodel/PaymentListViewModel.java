package com.example.paymentlist.viewmodel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.example.paymentlist.network.PaymentNetwork;
import com.example.paymentlist.model.ListResult;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentListViewModel extends ViewModel implements LifecycleObserver {

    private final PaymentNetwork paymentNetwork;

    private final MutableLiveData<ListResult> paymentList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    @Inject
    public PaymentListViewModel(PaymentNetwork paymentNetwork) {
        this.paymentNetwork = paymentNetwork;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onCreate() {
        paymentNetwork.getPayments().enqueue(new Callback<ListResult>() {
            @Override
            public void onResponse(Call<ListResult> call, Response<ListResult> response) {
                if (response.isSuccessful()) {
                    paymentList.postValue(response.body());
                } else {
                    errorMessage.postValue("Deu ruim, status code:" + response.code());
                }
            }

            @Override
            public void onFailure(Call<ListResult> call, Throwable t) {
                errorMessage.postValue("Deu ruim: " + t);
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void onDestroy() {
        paymentNetwork.getPayments().cancel();
    }

    public MutableLiveData<ListResult> getPaymentListObserver() {
        return paymentList;
    }

    public MutableLiveData<String> getErrorMessageObserver() {
        return errorMessage;
    }
}
