package com.noks1i.cocktailapp.ui.RandomCocktailFragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.noks1i.cocktailapp.R
import com.noks1i.cocktailapp.core.Resource
import com.noks1i.cocktailapp.data.remote.CocktailDataSource
import com.noks1i.cocktailapp.databinding.FragmentRandomCocktailBinding
import com.noks1i.cocktailapp.presentation.CocktailViewModel
import com.noks1i.cocktailapp.presentation.CocktailViewModelFactory
import com.noks1i.cocktailapp.repository.CocktailRepoImpl
import com.noks1i.cocktailapp.repository.RetrofitClient

/**
 * A simple [Fragment] subclass.
 * Use the [RandomCocktailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RandomCocktailFragment : Fragment(R.layout.fragment_random_cocktail) {

    private lateinit var binding: FragmentRandomCocktailBinding
    private val viewModel by viewModels<CocktailViewModel> {
        CocktailViewModelFactory(
            CocktailRepoImpl(
                CocktailDataSource(RetrofitClient.webService)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRandomCocktailBinding.bind(view)

        binding.root.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.placeholder
            )
        )

        viewModel.fetchRandomCocktail().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val idRandom = result.data.drinks[0].idDrink
                    goToDelayDetails(idRandom)
                }
                is Resource.Failure -> {
                }
            }
        })
    }

    private fun goToDelayDetails(id: String) {
        val action =
            RandomCocktailFragmentDirections.actionRandomCocktailFragmentToCocktailDetailFragment(
                id
            )
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(action, NavOptions.Builder().setPopUpTo(R.id.randomCocktailFragment, true).build())
        }, 2400)
    }
}