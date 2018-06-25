package com.example.pawel.simplegallery.api

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {

    @GET("photos")
    fun getListByAlbumId(@Query("albumId") albumId: Int) : Flowable<List<Photo>>

    @GET("photos")
    fun getListById(@Query("id") id: Int) : Flowable<List<Photo>>

}

data class Photo(
        val albumId: Int,
        val id:Int,
        val title:String,
        val url:String,
        val thumbnailUrl:String
)