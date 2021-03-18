package com.noks1i.cocktailapp.data.model

data class Cocktail(
    val idDrink: String = "",
    val strDrink: String = "",
    val strDrinkThumb: String = ""
)

data class CocktailList(val drinks: List<Cocktail> = listOf())