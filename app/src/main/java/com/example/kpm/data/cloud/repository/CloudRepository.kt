package com.example.kpm.data.cloud.repository

import com.example.kpm.data.cloud.ResultWrapper
import com.example.kpm.data.cloud.rest.PartnerService
import com.example.kpm.data.cloud.rest.PeanutService
import com.example.kpm.data.cloud.safeApiCall
import com.example.kpm.model.*
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PeanutCloudRepository(
    private val api: PeanutService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BasePeanutCloudRepository {
    override suspend fun loginValidate(
        userCredentials: UserCredentials,
    ): ResultWrapper<UserValidationResult> {
        return safeApiCall(dispatcher) {
            api.loginValidation(userCredentials)
        }
    }

    override suspend fun getUserInformation(userCredentials: JsonObject): ResultWrapper<UserData> {
        return safeApiCall(dispatcher){
            api.getUserInformation(userCredentials)
        }
    }

    override suspend fun getLastFourNumbersPhone(userCredentials: JsonObject): ResultWrapper<String> {
        return safeApiCall(dispatcher){
            api.getLastFourNumbersPhone(userCredentials)
        }
    }
}

class PartnerCloudRepository(
    private val api: PartnerService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BasePartnerCloudRepository{
    override suspend fun loginValidatePartner(userCredentials: UserCredentials): ResultWrapper<String> {
        return safeApiCall(dispatcher){
            api.loginValidationPartner(userCredentials)
        }
    }

    override suspend fun getPromos(
        login: Int,
        token: String,
        tradingSystem: Int,
        pairs: String,
        from: String,
        to: String
    ): ResultWrapper<ArrayList<Archive>> {
        return safeApiCall(dispatcher){
            api.getArchives(login,token, tradingSystem = 3, pairs, from, to)
        }
    }


}