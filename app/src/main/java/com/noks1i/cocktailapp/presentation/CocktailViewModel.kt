package com.noks1i.cocktailapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.noks1i.cocktailapp.core.Resource
import com.noks1i.cocktailapp.repository.CocktailRepo
import kotlinx.coroutines.Dispatchers

class CocktailViewModel(private val repo: CocktailRepo) : ViewModel() {
    fun fetchCocktails() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getCocktails()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchCocktailDetails(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getDetailsById(id)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchRandomCocktail() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getRandomCocktail()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class CocktailViewModelFactory(private val repo: CocktailRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CocktailRepo::class.java).newInstance(repo)
    }
}