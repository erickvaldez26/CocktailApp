package com.noks1i.cocktailapp.ui.CocktailDetailFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.noks1i.cocktailapp.core.Resource
import com.noks1i.cocktailapp.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCocktailDetailBinding.bind(view)

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_out)

        binding.btnImageCocktail.startAnimation(animation)
        binding.txtNameCocktail.startAnimation(animation)
        binding.lblPrepare.startAnimation(animation)
        binding.txtPrepareCocktail.startAnimation(animation)
        binding.txtIdCocktail.startAnimation(animation)

        viewModel.fetchCocktailDetails("15288").observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    Log.d("Goo", "Data: ${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("Goo", "Nooooooo ${result.exception}")
                }
            }
        })
    }
}