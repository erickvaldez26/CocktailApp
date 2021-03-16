package com.noks1i.cocktailapp.ui.CocktailDetailFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.noks1i.cocktailapp.R
import com.noks1i.cocktailapp.databinding.FragmentCocktailDetailBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CocktailDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 * Created by noks1i
 */
class CocktailDetailFragment : Fragment(R.layout.fragment_cocktail_detail) {

    private lateinit var binding: FragmentCocktailDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCocktailDetailBinding.bind(view)
    }
}