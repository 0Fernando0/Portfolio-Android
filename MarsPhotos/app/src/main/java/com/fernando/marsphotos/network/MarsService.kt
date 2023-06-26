package com.fernando.marsphotos.network

import retrofit2.http.GET


interface MarsService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}