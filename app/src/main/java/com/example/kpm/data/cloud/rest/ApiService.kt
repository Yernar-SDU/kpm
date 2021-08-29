package com.example.kpm.data.cloud.rest

import com.example.kpm.model.*
import com.google.gson.JsonObject
import retrofit2.http.*

interface PeanutService {
    @Headers("Content-Type: application/json")
    @POST("ClientCabinetBasic/IsAccountCredentialsCorrect")
    suspend fun loginValidation(
        @Body login: UserCredentials,
    ): UserValidationResult

    @POST("ClientCabinetBasic/GetAccountInformation")
    suspend fun getUserInformation(
        @Body userCredentials: JsonObject,
    ): UserData

    @Headers("Content-Type: application/json")
    @POST("ClientCabinetBasic/GetLastFourNumbersPhone")
    suspend fun getLastFourNumbersPhone(
        @Body userCredentials: JsonObject,
    ): String
}

interface PartnerService{
    @Headers("Content-Type: application/json")
    @POST("api/Authentication/RequestMoblieCabinetApiToken")
    suspend fun loginValidationPartner(
        @Body login: UserCredentials,
    ): String

    @GET("clientmobile/GetAnalyticSignals/{login}")
    suspend fun getArchives(
        @Path ("login") login: Int,
        @Header("passkey") token: String,
        @Query ("tradingsystem") tradingSystem: Int,
        @Query ("pairs") pairs: String,
        @Query ("from") from: String,
        @Query ("to") to: String,
    ): ArrayList<Archive>
}

