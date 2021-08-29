package com.example.kpm.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kpm.core.BaseViewModel
import com.example.kpm.data.cloud.ResultWrapper
import com.example.kpm.data.cloud.repository.BasePeanutCloudRepository
import com.example.kpm.data.prefs.Prefs
import com.example.kpm.model.UserData
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val prefs: Prefs,
    val basePeanutCloudRepository: BasePeanutCloudRepository
    ): BaseViewModel() {
    val userData = MutableLiveData(UserData())
    val phoneNumber = MutableLiveData(String())

    fun isAuthorized(): Boolean{
        return prefs.isAuthorized()
    }

    fun setAuthorized(isAuthorized: Boolean){
        prefs.setAuthorized(isAuthorized)
    }

    fun getUserData(){
        launchIO {
            val jsonObject = JsonObject()
            jsonObject.addProperty("login", prefs.getLogin().toInt())
            jsonObject.addProperty("token", prefs.getTokenPeanut())
            Log.i("yernar", "getUserData: ${prefs.getLogin()}")
            Log.i("yernar", "getUserData: ${prefs.getTokenPeanut()}")
            val wrapper = basePeanutCloudRepository.getUserInformation(jsonObject)

            when(wrapper){
                is ResultWrapper.Success -> {
                    Log.i("yernar", "getUserData: ${wrapper.value}")
                    userData.postValue(wrapper.value)
                }
                is ResultWrapper.Error -> {
                    Log.i("yernar", "getUserData: ${wrapper.error}")
                }
            }
        }
    }

    fun getLastFourNumbersPhone(){
        launchIO {
            val jsonObject = JsonObject()
            jsonObject.addProperty("login", prefs.getLogin().toInt())
            jsonObject.addProperty("token", prefs.getTokenPeanut())
            val wrapper = basePeanutCloudRepository.getLastFourNumbersPhone(jsonObject)

            when(wrapper){
                is ResultWrapper.Success -> {
                    Log.i("yernar", "getUserData: ${wrapper.value}")
                    phoneNumber.postValue(wrapper.value)
                }
                is ResultWrapper.Error -> {
                    Log.i("yernar", "getUserData: ${wrapper.error}")
                }
            }
        }
    }

    fun logout(){
        prefs.setTokenPartner("")
        prefs.setTokenPeanut("")
        prefs.setAuthorized(false)
        prefs.setLogin("")
    }

}