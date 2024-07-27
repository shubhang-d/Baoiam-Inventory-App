package com.example.baoiaminventoryapp.di

import android.app.Application
import android.content.Context
import com.example.baoiaminventoryapp.api.BarcodeLookupService
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(ViewModelComponent::class)
@Module
object AppModule {


    @ViewModelScoped
    @Provides
    fun provideContext(app:Application):Context{
        return app.applicationContext
    }

    @ViewModelScoped
    @Provides
    fun provideBarCodeOptions() : GmsBarcodeScannerOptions{
        return GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .build()
    }

    @ViewModelScoped
    @Provides
    fun provideBarCodeScanner(context: Context,options: GmsBarcodeScannerOptions):GmsBarcodeScanner{
        return GmsBarcodeScanning.getClient(context, options)
    }

    @Provides
    @ViewModelScoped
    fun provideRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.barcodelookup.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @ViewModelScoped
    fun provideBarcodeLookupService(retrofit: Retrofit): BarcodeLookupService {
        return retrofit.create(BarcodeLookupService::class.java)
    }
}