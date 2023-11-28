package com.example.allergenfinder.data.network

import com.example.allergenfinder.R

sealed class NetworkResponse<out T> {
    open fun getOrNull(): T? {
        return when(this){
            is Success -> data
            is Error -> null
        }
    }

    data class Success<out T>(val data: T) : NetworkResponse<T>()
    sealed class Error<out T>(open val messageId: Int) : NetworkResponse<T>() {
        data class Access<out T>(
            override val messageId: Int = R.string.lbl_access_error_message
        ) : Error<T>(messageId)

        data class Authorization<out T>(
            override val messageId: Int = R.string.lbl_authorization_error_message
        ) : Error<T>(messageId)

        data class Network<out T>(
            override val messageId: Int = R.string.lbl_network_error_message
        ) : Error<T>(messageId)

        data class NotFound<out T>(
            override val messageId: Int = R.string.lbl_not_found_error_message
        ) : Error<T>(messageId)

        data class Server<out T>(
            override val messageId: Int = R.string.lbl_server_error_message
        ) : Error<T>(messageId)
    }
}
