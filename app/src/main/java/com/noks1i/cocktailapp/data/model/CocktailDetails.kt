package com.noks1i.cocktailapp.data.model

data class CocktailDetails(
    val idDrink: String = "",
    val strDrink: String = "",
    val strTags: String = "",
    val strCategory: String = "",
    val strAlcoholic: String = "",
    val strInstructions: String = "",
    val strDrinkThumb: String = "",
)

data class CocktailEntry(val drinks: List<CocktailDetails> = listOf())