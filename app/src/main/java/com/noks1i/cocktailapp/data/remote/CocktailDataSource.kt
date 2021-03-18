package com.noks1i.cocktailapp.data.remote

import com.noks1i.cocktailapp.data.model.Cocktail
import com.noks1i.cocktailapp.data.model.CocktailList
import com.noks1i.cocktailapp.repository.WebService

class CocktailDataSource(private val webService: WebService) {
    suspend fun getCocktail(): CocktailList = webService.getCocktails("Alcoholic")
}