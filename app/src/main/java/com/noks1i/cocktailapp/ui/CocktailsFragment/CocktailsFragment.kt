package com.noks1i.cocktailapp.ui.CocktailsFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
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
import com.noks1i.cocktailapp.ui.CocktailDetailFragment.anim.startAnimation
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

        val animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.explotion_anim).apply {
                duration = 700
                interpolator = AccelerateDecelerateInterpolator()
            }

        binding.btnRandom.setOnClickListener {
            binding.btnRandom.isVisible = false
            binding.circle.isVisible = true
            binding.circle.startAnimation(animation) {
                // Display your fragment
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.placeholder
                    )
                )
                binding.circle.isVisible = false
                findNavController().navigate(R.id.action_cocktailsFragment_to_randomCocktailFragment)
            }
        }

        viewModel.fetchCocktails().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.shimmer.startShimmer()
                    binding.shimmer.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.shimmer.stopShimmer()
                    binding.shimmer.visibility = View.GONE
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
        val action =
            CocktailsFragmentDirections.actionCocktailsFragmentToCocktailDetailFragment(cocktail.idDrink)
        findNavController().navigate(action)
    }
}