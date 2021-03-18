package com.noks1i.cocktailapp.ui.CocktailsFragment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noks1i.cocktailapp.core.BaseViewHolder
import com.noks1i.cocktailapp.data.model.Cocktail
import com.noks1i.cocktailapp.databinding.CocktailItemBinding

class CocktailAdapter(
    private val cocktailList: List<Cocktail>,
    private val itemClickListener: OnCocktailClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnCocktailClickListener {
        fun onCocktailClick(cocktail: Cocktail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            CocktailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = CocktailViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onCocktailClick(cocktailList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is CocktailViewHolder -> holder.bind(cocktailList[position])
        }
    }

    override fun getItemCount(): Int = cocktailList.size

    private inner class CocktailViewHolder(val binding: CocktailItemBinding, val context: Context) :
        BaseViewHolder<Cocktail>(binding.root) {
        override fun bind(item: Cocktail) {
            Glide.with(context).load(item.strDrinkThumb).centerCrop().into(binding.imgCocktailItem)
            binding.txtNameCocktailItem.text = item.strDrink
            binding.txtIdCocktailItem.text = item.idDrink
        }
    }
}