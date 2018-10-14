package com.joaoibarra.gitapp.api

import com.joaoibarra.dogao.api.models.DogResponse
import com.joaoibarra.dogao.api.models.LoginResponse
import io.reactivex.Observable
import retrofit2.http.*

public interface DogaoApi {

    @Headers(
            "Content-Type: application/json"
    )
    @POST("signup")
    fun signUp(@Field("email") email: String = "") : Observable<LoginResponse>

    @GET("feed")
    fun getDogs(
        @Header("Authorization") token: String,
        @Query("category") category: String = "husky") : Observable<DogResponse>

}