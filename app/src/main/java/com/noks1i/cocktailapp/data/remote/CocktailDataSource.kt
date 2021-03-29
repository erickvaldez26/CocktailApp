package com.noks1i.cocktailapp.data.remote

import com.noks1i.cocktailapp.data.model.CocktailEntry
import com.noks1i.cocktailapp.data.model.CocktailList
import com.noks1i.cocktailapp.repository.WebService

class CocktailDataSource(private val webService: WebService) {
    suspend fun getCocktail(): CocktailList = webService.getCocktails("Alcoholic")
    suspend fun getDetailsById(id: String): CocktailEntry = webService.getDetailsById(id)
}