package com.example.searchviewgit.network


import com.example.searchviewgit.model.User
import com.example.searchviewgit.model.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiUser {

    @GET("/search/users?q={username}")
    fun getSearchUser(@Query("username")username: String): Observable<List<UserResponse>>

    @GET("/search/users?q={username}")
    fun getUser(@Query("username")username: String): Observable<List<User>>
}