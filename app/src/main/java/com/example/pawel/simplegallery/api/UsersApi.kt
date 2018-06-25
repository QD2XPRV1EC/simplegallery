package com.example.pawel.simplegallery.api

import com.google.gson.annotations.SerializedName
import io.reactivex.Flowable
import retrofit2.http.GET

interface UsersApi {

    @GET("users")
    fun getList(): Flowable<List<User>>


}

data class User(
        val id: Int,
        val name: String,
        val username: String,
        val email: String,
        val address: UserAddress,
        val phone: String,
        val website: String,
        val company: UserCompany
)

data class UserAddress(
        val street: String,
        val suite: String,
        val city: String,
        @SerializedName("zipcode") val zipCode: String,
        val geo: UserAddressGeo
)

data class UserAddressGeo(
        val lat: String,
        val lng: String
)

data class UserCompany(
        val name: String,
        val catchPhrase: String,
        val bs: String
)