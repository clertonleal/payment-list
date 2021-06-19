package com.example.paymentlist;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;

import com.example.paymentlist.model.ListResult;
import com.example.paymentlist.network.PaymentNetwork;
import com.example.paymentlist.viewmodel.PaymentListViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PaymentListViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private PaymentListViewModel viewModel;
    private PaymentNetwork network;
    private Observer<Boolean> progressObserver;
    private Observer<String> errorObserver;
    private Observer<ListResult> paymentsObserver;
    private Call<ListResult> mockedCall;
    private LifecycleRegistry lifecycle;

    @Before
    public void setUp() {
        progressObserver = mock(Observer.class);
        errorObserver = mock(Observer.class);
        paymentsObserver = mock(Observer.class);

        mockedCall = mock(Call.class);

        network = mock(PaymentNetwork.class);
        viewModel = new PaymentListViewModel(network);

        when(network.getPayments()).thenReturn(mockedCall);

        viewModel.getProgressObserver().observeForever(progressObserver);
        viewModel.getErrorMessageObserver().observeForever(errorObserver);
        viewModel.getPaymentListObserver().observeForever(paymentsObserver);

        lifecycle = new LifecycleRegistry(mock(LifecycleOwner.class));
        lifecycle.addObserver(viewModel);
    }

    @Test
    public void whenPaymentsServiceReturnSuccessShouldDisableProgressAndShowResults() {
        doAnswer(invocation -> {
            Callback<ListResult> callback = invocation.getArgument(0);
            callback.onResponse(mockedCall, Response.success(new ListResult()));
            return null;
        }).when(mockedCall).enqueue(any(Callback.class));

        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);

        verify(progressObserver, times(2)).onChanged(any());
        assertFalse(viewModel.getProgressObserver().getValue());
        verify(paymentsObserver).onChanged(any(ListResult.class));
    }
}