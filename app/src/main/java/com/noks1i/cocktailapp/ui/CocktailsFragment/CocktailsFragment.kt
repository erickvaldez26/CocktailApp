package com.noks1i.cocktailapp.ui.CocktailsFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.noks1i.cocktailapp.R
import com.noks1i.cocktailapp.core.Resource
import com.noks1i.cocktailapp.data.model.Cocktail
import com.noks1i.cocktailapp.data.remote.CocktailDataSource
import com.noks1i.cocktailapp.databinding.FragmentCocktailsBinding
import com.noks1i.cocktailapp.presentation.CocktailViewModel
import com.noks1i.cocktailapp.presentation.CocktailViewModelFactory
import com.noks1i.cocktailapp.repository.CocktailRepoImpl
import com.noks1i.cocktailapp.repository.RetrofitClient
import com.noks1i.cocktailapp.ui.CocktailDetailFragment.CocktailDetailFragment
import com.noks1i.cocktailapp.ui.CocktailsFragment.adapters.CocktailAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [CocktailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 * Created by noks1i
 */
class CocktailsFragment : Fragment(R.layout.fragment_cocktails),
    CocktailAdapter.OnCocktailClickListener {

    private lateinit var binding: FragmentCocktailsBinding
    private val viewModel by viewModels<CocktailViewModel> {
        CocktailViewModelFactory(CocktailRepoImpl(CocktailDataSource(RetrofitClient.webService)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCocktailsBinding.bind(view)

        viewModel.fetchCocktails().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("Vamos", "Loading...")
                }
                is Resource.Success -> {
                    Log.d("Vamos", "${result.data}")
                    binding.rvCocktails.adapter = CocktailAdapter(
                        result.data.drinks, this@CocktailsFragment
                    )
                }
                is Resource.Failure -> {
                    Log.d("Vamos", "Nooooooo ${result.exception}")
                }
            }
        })
    }

    override fun onCocktailClick(cocktail: Cocktail) {
        findNavController().navigate(R.id.cocktailDetailFragment)
    }
}