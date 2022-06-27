package com.codecanyon.umes.repository

import com.codecanyon.umes.api.UmesAPI
import com.codecanyon.umes.util.AppUtils
import com.codecanyon.umes.util.Singleton
import io.reactivex.Single
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LoginRepository {
    fun loginToUser(requestBody: RequestBody) : Single<Map<String, Any>> {
        return AppUtils.getUmesAPI().loginUser(requestBody)
    }
}