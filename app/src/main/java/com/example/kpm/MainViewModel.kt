package com.example.kpm

import com.example.kpm.core.BaseViewModel
import com.example.kpm.data.cloud.ResultWrapper
import com.example.kpm.data.cloud.repository.BasePeanutCloudRepository
import com.example.kpm.data.prefs.Prefs
import com.example.kpm.model.UserCredentials
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.time.milliseconds
import kotlin.time.minutes
import kotlin.time.seconds

@HiltViewModel
class MainViewModel @Inject constructor(val prefs: Prefs, val basePeanutCloudRepository: BasePeanutCloudRepository): BaseViewModel() {

    fun isAuthorized(): Boolean{
        return prefs.isAuthorized()
    }

}