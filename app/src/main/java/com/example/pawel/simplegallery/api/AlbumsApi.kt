package com.example.pawel.simplegallery.api

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumsApi {

    @GET("albums")
    fun getListByUserId(@Query("userId") userId: Int) : Flowable<List<Album>>


}

data class Album (
        val userId: Int,
        val id: Int,
        val title: String
)