package com.noks1i.cocktailapp.repository

import com.noks1i.cocktailapp.data.model.CocktailList

interface CocktailRepo {
    suspend fun getCocktails(): CocktailList
}