package com.noks1i.cocktailapp.ui.CocktailDetailFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.noks1i.cocktailapp.R
import com.noks1i.cocktailapp.core.Resource
import com.noks1i.cocktailapp.data.remote.CocktailDataSource
import com.noks1i.cocktailapp.databinding.FragmentCocktailDetailBinding
import com.noks1i.cocktailapp.presentation.CocktailViewModel
import com.noks1i.cocktailapp.presentation.CocktailViewModelFactory
import com.noks1i.cocktailapp.repository.CocktailRepoImpl
import com.noks1i.cocktailapp.repository.RetrofitClient

/**
 * A simple [Fragment] subclass.
 * Use the [CocktailDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 * Created by noks1i
 */
class CocktailDetailFragment : Fragment(R.layout.fragment_cocktail_detail) {

    private lateinit var binding: FragmentCocktailDetailBinding
    private val viewModel by viewModels<CocktailViewModel> {
        CocktailViewModelFactory(CocktailRepoImpl(CocktailDataSource((RetrofitClient.webService))))
    }
    private val args: CocktailDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCocktailDetailBinding.bind(view)

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_out)

        binding.imgCocktail.startAnimation(animation)
        binding.txtNameCocktail.startAnimation(animation)
        binding.lblPrepare.startAnimation(animation)
        binding.txtPrepareCocktail.startAnimation(animation)
        binding.txtIdCocktail.startAnimation(animation)

        getRandomCocktail()

        onBackPressedFragment()
    }

    private fun onBackPressedFragment() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(
                R.id.cocktailsFragment, null,
                NavOptions.Builder().setPopUpTo(R.id.cocktailDetailFragment, true).build()
            )
        }
    }

    private fun getRandomCocktail() {
        viewModel.fetchCocktailDetails(args.idDrink)
            .observe(viewLifecycleOwner, { result ->
                when (result) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        binding.imgCocktail.background = null
                        binding.txtNameCocktail.background = null
                        binding.lblPrepare.background = null
                        binding.lblPrepare.text = getString(R.string.preparation)
                        binding.txtPrepareCocktail.background = null
                        binding.txtIdCocktail.background = null
                        result.data.drinks.forEach { drink ->
                            Glide.with(requireContext()).load(drink.strDrinkThumb).centerCrop()
                                .into(binding.imgCocktail)
                            binding.txtNameCocktail.text = drink.strDrink
                            binding.txtPrepareCocktail.text = drink.strInstructions
                            binding.txtIdCocktail.text = drink.idDrink
                        }
                    }
                    is Resource.Failure -> {
                        Log.d("Goo", "Nooooooo ${result.exception}")
                    }
                }
            })
    }
}