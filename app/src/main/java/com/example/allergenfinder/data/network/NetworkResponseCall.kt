package com.example.allergenfinder.data.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.net.ssl.HttpsURLConnection

class NetworkResponseCall<T>(
    private val call: Call<T>
) : CallDelegate<T, NetworkResponse<T>>(call) {
    override fun enqueueImpl(callback: Callback<NetworkResponse<T>>) {
        call.enqueue(callback(callback))
    }

    override fun cloneImpl(): Call<NetworkResponse<T>> = NetworkResponseCall(call.clone())

    private fun callback(callback: Callback<NetworkResponse<T>>) = object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            val body = response.body()
            val code = response.code()
            val networkResponse: NetworkResponse<T> = when {
                response.isSuccessful && body != null -> NetworkResponse.Success<T>(body)
                response.isSuccessful && body == null -> NetworkResponse.Error.NotFound()
                code == HttpsURLConnection.HTTP_UNAUTHORIZED -> NetworkResponse.Error.Authorization()
                code == HttpsURLConnection.HTTP_INTERNAL_ERROR -> NetworkResponse.Error.Server()
                code == HttpsURLConnection.HTTP_NOT_FOUND -> NetworkResponse.Error.NotFound()
                else -> NetworkResponse.Error.Access()
            }

            val success = Response.success(networkResponse)
            callback.onResponse(this@NetworkResponseCall, success)
        }

        override fun onFailure(call: Call<T>, throwable: Throwable) {
            val networkResponse: NetworkResponse<T> = when (throwable) {
                is IOException -> NetworkResponse.Error.Network()
                else -> NetworkResponse.Error.Access()
            }
            val success = Response.success(networkResponse)
            callback.onResponse(this@NetworkResponseCall, success)
        }
    }
}
