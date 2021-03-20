package com.noks1i.cocktailapp.repository

import com.noks1i.cocktailapp.data.model.CocktailEntry
import com.noks1i.cocktailapp.data.model.CocktailList

interface CocktailRepo {
    suspend fun getCocktails(): CocktailList
    suspend fun getDetailsById(id: String): CocktailEntry
}