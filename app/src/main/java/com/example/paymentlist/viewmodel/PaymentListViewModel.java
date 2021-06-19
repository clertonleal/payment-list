package com.example.paymentlist.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.paymentlist.model.ListResult;
import com.example.paymentlist.network.PaymentNetwork;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class PaymentListViewModel {

    private final PaymentNetwork paymentNetwork;

    private final MutableLiveData<ListResult> paymentList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> progress = new MutableLiveData<>();

    @Inject
    public PaymentListViewModel(PaymentNetwork paymentNetwork) {
        this.paymentNetwork = paymentNetwork;
    }

    public void loadPayments() {
        progress.postValue(true);
        paymentNetwork.getPayments().enqueue(new Callback<ListResult>() {
            @Override
            public void onResponse(@NotNull Call<ListResult> call, @NotNull Response<ListResult> response) {
                if (response.isSuccessful()) {
                    paymentList.postValue(response.body());
                } else {
                    errorMessage.postValue("We have a internal error: " + response.code());
                    Timber.e("Error to request payments: %s", response.toString());
                }

                progress.postValue(false);
            }

            @Override
            public void onFailure(@NotNull Call<ListResult> call, @NotNull Throwable t) {
                errorMessage.postValue("We can't access the page. =/ \nPlease verify you internet connection");
                progress.postValue(false);
                Timber.e(t, "Internal error to call payments");
            }
        });
    }

    public void cancelNetworkCall() {
        paymentNetwork.getPayments().cancel();
    }

    public LiveData<ListResult> getPaymentListObserver() {
        return paymentList;
    }

    public LiveData<String> getErrorMessageObserver() {
        return errorMessage;
    }

    public LiveData<Boolean> getProgressObserver() {
        return progress;
    }
}
