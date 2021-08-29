package com.example.kpm.ui.archive

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kpm.core.BaseViewModel
import com.example.kpm.data.cloud.ResultWrapper
import com.example.kpm.data.cloud.repository.BasePartnerCloudRepository
import com.example.kpm.data.prefs.Prefs
import com.example.kpm.model.Archive
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject() constructor(
    val prefs: Prefs,
    val basePartnerCloudRepository: BasePartnerCloudRepository
): BaseViewModel() {

    val tokenIsInvalid = MutableLiveData(true)
    val promos = MutableLiveData(ArrayList<Archive>())
    val loading = MutableLiveData(false)
    fun isAuthorized(): Boolean{
        return prefs.isAuthorized()
    }

    fun getArchives(pairs: String, from: String, to: String){
        launchIO {
            loading.postValue(true)
            Log.i("yernar", "onViewCreated promo fragment partner token: ${prefs.getTokenPartner()}")
            val wrapper = basePartnerCloudRepository.getPromos(prefs.getLogin().toInt(), prefs.getTokenPartner(), 3, pairs, from, to)

            when(wrapper){
                is ResultWrapper.Success -> {
                    promos.postValue(wrapper.value)
                }
                is ResultWrapper.Error -> {
                    loading.postValue(false)
                    tokenIsInvalid.postValue(false)
                }
            }
            loading.postValue(false)
        }
    }
}
