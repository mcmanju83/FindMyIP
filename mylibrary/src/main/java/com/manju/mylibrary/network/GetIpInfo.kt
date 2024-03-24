package com.manju.mylibrary.network

import com.manju.mylibrary.data.IpInfo
import com.manju.mylibrary.remote.FindMyIpApi
import com.manju.mylibrary.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetIpInfo(
    private val api: FindMyIpApi
){
    fun getIpInfo() : Flow<Resource<IpInfo>> = flow {
        emit(Resource.Loading())

        var remoteData: IpInfo? = null
        try {
            remoteData = api.getIpInfo()
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = remoteData
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = remoteData
            ))
        }

        emit(Resource.Success(remoteData))
    }
}