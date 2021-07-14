package com.example.paymentlist.injection;

import com.example.paymentlist.PaymentApplication;
import com.example.paymentlist.network.PaymentNetwork;
import com.example.paymentlist.router.PaymentRouter;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
final class PaymentComponent {

    @Provides
    static PaymentNetwork providePaymentService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/optile/checkout-android/develop/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(PaymentNetwork.class);
    }

    @Provides
    static PaymentRouter providePaymentRouter() {
        return new PaymentRouter(PaymentApplication.context);
    }
}
