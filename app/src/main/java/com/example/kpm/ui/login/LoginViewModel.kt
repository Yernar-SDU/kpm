package com.example.kpm.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kpm.core.BaseViewModel
import com.example.kpm.data.cloud.ResultWrapper
import com.example.kpm.data.cloud.repository.BasePartnerCloudRepository
import com.example.kpm.data.cloud.repository.BasePeanutCloudRepository
import com.example.kpm.data.prefs.Prefs
import com.example.kpm.model.UserCredentials
import com.example.kpm.model.UserValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val basePeanutCloudRepository: BasePeanutCloudRepository,
    val basePartnerCloudRepository: BasePartnerCloudRepository,
    val prefs: Prefs
    ): BaseViewModel() {

    val userValidationResult = MutableLiveData(UserValidationResult())
    val tokenPartner = MutableLiveData("")
    val result = MutableLiveData(false)
    val loading1 = MutableLiveData(false)
    val loading2 = MutableLiveData(false)

    fun setAuthorized(isAuthorized: Boolean){
        prefs.setAuthorized(isAuthorized)
    }

    fun isAuthorized(): Boolean{
        return prefs.isAuthorized()
    }


    fun loginValidate(login: String, password: String){
        launchIO {
            loading1.postValue(true)
            val jsonObject = UserCredentials(login.toInt(), password)

            val wrapper = basePeanutCloudRepository.loginValidate(jsonObject)

            when(wrapper) {
                is ResultWrapper.Success -> {
                    userValidationResult.postValue(wrapper.value)
                    prefs.setTokenPeanut(wrapper.value.token)
                    prefs.setLogin(login)
                }
                is ResultWrapper.Error -> {
                    result.postValue(false)
                    loading1.postValue(false)
                }
            }
            loading1.postValue(false)
        }
    }

    fun loginValidatePartner(login: String, password: String){
        launchIO {
            loading2.postValue(true)
            val jsonObject = UserCredentials(login.toInt(), password)
            val wrapper = basePartnerCloudRepository.loginValidatePartner(jsonObject)
            when(wrapper) {
                is ResultWrapper.Success -> {
                    setAuthorized(true)
                    Log.i("yernar", "loginValidate partner: ${wrapper.value}")
                    tokenPartner.postValue(wrapper.value)
                    prefs.setTokenPartner(wrapper.value)
                    Log.i("yernar", "getPromos: ${prefs.getTokenPartner()}")
                }
                is ResultWrapper.Error -> {
                    result.postValue(false)
                    loading2.postValue(false)
                    Log.i("yernar", "loginValidate: ${wrapper.toString()}")
                }

            }
            delay(1000)
            loading2.postValue(false)
        }
    }

    fun getCurrentDateTime(): Int {
        return Calendar.getInstance().time.seconds
    }
}