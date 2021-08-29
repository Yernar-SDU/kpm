package com.example.kpm.di

import android.util.JsonReader
import com.example.kpm.core.Config
import com.example.kpm.data.cloud.repository.BasePartnerCloudRepository
import com.example.kpm.data.cloud.repository.BasePeanutCloudRepository
import com.example.kpm.data.cloud.repository.PartnerCloudRepository
import com.example.kpm.data.cloud.repository.PeanutCloudRepository
import com.example.kpm.data.cloud.rest.PartnerService
import com.example.kpm.data.cloud.rest.PeanutService
import com.example.kpm.utils.ErrorCodeInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(Config.HOST)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(interceptor)
        client.addInterceptor(ErrorCodeInterceptor())
        return client.build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }



    @Singleton
    @Provides
    fun providePeanutService(retrofit: Retrofit): PeanutService {
        return retrofit.create(PeanutService::class.java)
    }


    @Provides
    fun providePeanutCloudRepository(apIs: PeanutService): BasePeanutCloudRepository {
        return PeanutCloudRepository(
            apIs
        )
    }



    @Singleton
    @Provides
    fun providePartnerService(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): PartnerService {
        val retrofit2 = Retrofit.Builder().baseUrl(Config.HOST_PARTNER)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
        return retrofit2.create(PartnerService::class.java)
    }


    @Provides
    fun providePartnerCloudRepository(apIs: PartnerService): BasePartnerCloudRepository {
        return PartnerCloudRepository(
            apIs
        )
    }



}