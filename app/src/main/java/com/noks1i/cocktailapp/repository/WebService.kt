package com.noks1i.cocktailapp.repository

import com.google.gson.GsonBuilder
import com.noks1i.cocktailapp.data.model.CocktailEntry
import com.noks1i.cocktailapp.data.model.CocktailList
import com.noks1i.cocktailapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("filter.php")
    suspend fun getCocktails(@Query("a") data: String): CocktailList

    @GET("lookup.php")
    suspend fun getDetailsById(@Query("i") id: String): CocktailEntry
}

object RetrofitClient {
    val webService by lazy {
        Retrofit.Builder().baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
            .create(
                WebService::class.java
            )
    }
}