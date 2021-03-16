package com.noks1i.cocktailapp.ui.CocktailsFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.noks1i.cocktailapp.R
import com.noks1i.cocktailapp.databinding.FragmentCocktailsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CocktailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 * Created by noks1i
 */
class CocktailsFragment : Fragment(R.layout.fragment_cocktails) {

    private lateinit var binding: FragmentCocktailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCocktailsBinding.bind(view)
    }
}