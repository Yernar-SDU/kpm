package com.example.kpm.di

import android.content.Context
import android.content.SharedPreferences
import com.example.kpm.data.prefs.Prefs
import com.example.kpm.data.prefs.PrefsImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class PrefsModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        val prefs = context.getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE)
        return prefs
    }


    @Provides
    @Singleton
    fun providePrefs(context: Context, gson: Gson, sharedPreferences: SharedPreferences): Prefs {
        return PrefsImpl(gson, sharedPreferences)
    }


}