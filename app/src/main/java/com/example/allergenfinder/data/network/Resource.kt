package com.example.allergenfinder.data.network

import com.example.allergenfinder.R

sealed class Resource<out T> {
    val isSuccessful: Boolean
        get() = this is Success


    fun getOrNull(): T? {
        return when (this) {
            is Success -> data
            is Error -> null
        }
    }

    fun errorOrNull(): Error<T>? {
        return when (this) {
            is Error -> this
            is Success -> null
        }
    }

    data class Success<out T>(val data: T) : Resource<T>()
    sealed class Error<out T>(open val messageId: Int) : Resource<T>() {
        data class Access<out T>(
            override val messageId: Int = R.string.lbl_error_network_message
        ) : Error<T>(messageId)

        data class Authorization<out T>(
            override val messageId: Int = R.string.lbl_error_network_message
        ) : Error<T>(messageId)

        data class Network<out T>(
            override val messageId: Int = R.string.lbl_error_network_message
        ) : Error<T>(messageId)

        data class NotFound<out T>(
            override val messageId: Int = R.string.lbl_error_not_found_message
        ) : Error<T>(messageId)

        data class Server<out T>(
            override val messageId: Int = R.string.lbl_error_network_message
        ) : Error<T>(messageId)

        fun <U> getErrorType(): Error<U> {
            return when (this) {
                is Access -> Access()
                is Authorization -> Authorization()
                is Network -> Network()
                is NotFound -> NotFound()
                is Server -> Server()
            }
        }
    }
}
