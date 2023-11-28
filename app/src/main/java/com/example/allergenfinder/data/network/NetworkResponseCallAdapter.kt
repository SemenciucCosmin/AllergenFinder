package com.example.allergenfinder.data.network

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class NetworkResponseCallAdapter(
    private val type: Type
) : CallAdapter<Type, Call<NetworkResponse<Type>>> {
    override fun responseType(): Type = type

    override fun adapt(call: Call<Type>): Call<NetworkResponse<Type>> = NetworkResponseCall(call)
}
