package com.example.kpm.data.cloud.repository

import com.example.kpm.data.cloud.ResultWrapper
import com.example.kpm.model.*
import com.google.gson.JsonObject

interface BasePeanutCloudRepository{

    suspend fun loginValidate(userCredentials: UserCredentials): ResultWrapper<UserValidationResult>

    suspend fun getUserInformation(userCredentials: JsonObject): ResultWrapper<UserData>

    suspend fun getLastFourNumbersPhone(userCredentials: JsonObject): ResultWrapper<String>
}

interface BasePartnerCloudRepository{
    suspend fun loginValidatePartner(userCredentials: UserCredentials): ResultWrapper<String>

    suspend fun getPromos(login: Int,token: String, tradingSystem: Int, pairs: String, from: String, to: String ): ResultWrapper<ArrayList<Archive>>

}