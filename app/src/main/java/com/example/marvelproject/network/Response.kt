package com.example.marvelproject.network

sealed class Response<HeroDtoResponse>(val data: HeroDtoResponse? = null,
                                     val message: String? = null) {

    class Success<HeroDtoResponse>(message: String?, data: HeroDtoResponse?):
        Response<HeroDtoResponse>(message = message, data = data)

    class Error<HeroDtoResponse>(message: String?): Response<HeroDtoResponse>(message = message)

}
