package com.example.paymentlist;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.paymentlist.model.ListResult;
import com.example.paymentlist.network.PaymentNetwork;
import com.example.paymentlist.viewmodel.PaymentListViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
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

    @Before
    public void setUp() {
        mockedCall = mock(Call.class);
        network = mock(PaymentNetwork.class);
        when(network.getPayments()).thenReturn(mockedCall);

        viewModel = new PaymentListViewModel(network);
        progressObserver = mock(Observer.class);
        errorObserver = mock(Observer.class);
        paymentsObserver = mock(Observer.class);
        viewModel.getProgressObserver().observeForever(progressObserver);
        viewModel.getErrorMessageObserver().observeForever(errorObserver);
        viewModel.getPaymentListObserver().observeForever(paymentsObserver);
    }

    @Test
    public void whenPaymentsServiceReturnSuccessShouldDisableProgressAndShowResults() {
        ListResult listResult = new ListResult();
        doAnswer(invocation -> {
            Callback<ListResult> callback = invocation.getArgument(0);
            callback.onResponse(mockedCall, Response.success(listResult));
            return null;
        }).when(mockedCall).enqueue(any(Callback.class));

        viewModel.loadPayments();

        verify(progressObserver, times(2)).onChanged(any());
        verify(errorObserver, never()).onChanged(any());
        verify(paymentsObserver).onChanged(any());

        assertFalse(viewModel.getProgressObserver().getValue());
        assertEquals(listResult, viewModel.getPaymentListObserver().getValue());
    }

    @Test
    public void whenPaymentsServiceReturnBackendErrorShouldDisableProgressAndShowError() {
        doAnswer(invocation -> {
            Callback<ListResult> callback = invocation.getArgument(0);
            callback.onResponse(mockedCall, Response.error(404, mock(ResponseBody.class)));
            return null;
        }).when(mockedCall).enqueue(any(Callback.class));

        viewModel.loadPayments();

        verify(progressObserver, times(2)).onChanged(any());
        verify(errorObserver).onChanged(any());
        verify(paymentsObserver, never()).onChanged(any());

        assertFalse(viewModel.getProgressObserver().getValue());
        assertEquals("We have a internal error: 404", viewModel.getErrorMessageObserver().getValue());
    }

    @Test
    public void whenPaymentsServiceReturnInternalErrorShouldDisableProgressAndShowError() {
        doAnswer(invocation -> {
            Callback<ListResult> callback = invocation.getArgument(0);
            callback.onFailure(mockedCall, mock(Throwable.class));
            return null;
        }).when(mockedCall).enqueue(any(Callback.class));

        viewModel.loadPayments();

        verify(progressObserver, times(2)).onChanged(any());
        verify(errorObserver).onChanged(any());
        verify(paymentsObserver, never()).onChanged(any());

        assertFalse(viewModel.getProgressObserver().getValue());
        assertEquals("We can't access the page. =/ \nPlease verify you internet connection", viewModel.getErrorMessageObserver().getValue());
    }

    @Test
    public void whenLifecycleIsPausedShouldCancelRequest() {
        viewModel.cancelNetworkCall();

        verify(mockedCall).cancel();
    }
}