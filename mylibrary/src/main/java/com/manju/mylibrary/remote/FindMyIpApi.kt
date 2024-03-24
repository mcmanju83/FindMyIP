package com.manju.mylibrary.remote

import com.manju.mylibrary.data.IpInfo
import retrofit2.http.GET

interface FindMyIpApi {
    @GET("/json/")
    suspend fun getIpInfo(): IpInfo

    companion object {
        const val BASE_URL = "https://ipapi.co/"
    }
}