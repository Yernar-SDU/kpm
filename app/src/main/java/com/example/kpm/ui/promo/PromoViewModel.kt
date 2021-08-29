package com.example.kpm.ui.promo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kpm.core.BaseViewModel
import com.example.kpm.data.cloud.repository.BasePeanutCloudRepository
import com.example.kpm.data.prefs.Prefs
import com.example.kpm.ksoap.src.PWNBasicHttpsBinding_CabinetMicroService
import com.example.kpm.model.Promo
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.json.JSONObject;

@HiltViewModel
class PromoViewModel
@Inject constructor(
    val prefs: Prefs,
    val basePeanutCloudRepository: BasePeanutCloudRepository
) : BaseViewModel() {
    val tokenIsValid = MutableLiveData(true)
    val promos = MutableLiveData(ArrayList<Promo>())
    fun soap() {
        launchIO {
            val service =
                PWNBasicHttpsBinding_CabinetMicroService()
            val res = service.GetCCPromo("en")
            val res2 = res.replace("False", "false")
            Log.i("kokos", "initView: ${res2}")
            val jsonObject = JSONObject(res2.toString())
            val keys = jsonObject.keys()
            val arrayPromos = ArrayList<Promo>()
            while (keys.hasNext()) {
                val key = keys.next()
                val promoObject: JSONObject = jsonObject[key] as JSONObject
                val promo = Promo(
                    key,
                    promoObject["image"].toString(),
                    promoObject["text"].toString(),
                    promoObject["link"].toString(),
                    promoObject["button_text"].toString(),
                    promoObject["button_color"].toString(),
                    promoObject["euro_available"].toString().toBoolean(),
                    promoObject["die_date"].toString().toInt()

                )
                arrayPromos.add(promo)
                Log.i("kokos", "soap: ${promo.toString()}")
            }
            promos.postValue(arrayPromos)
        }
    }

}