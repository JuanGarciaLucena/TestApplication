package com.juanlucena.testapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanlucena.testapplication.R
import com.juanlucena.testapplication.databinding.AdapterCharacterBinding
import com.juanlucena.testapplication.domain.model.CharacterDomain

class CharacterAdapter(
    private val characterList: List<CharacterDomain>,
    private val onItemClick: (CharacterDomain) -> Unit,
    private val onFavouriteClick: (CharacterDomain) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun getItemCount() = characterList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = AdapterCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characterList[position], onItemClick, onFavouriteClick)
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = AdapterCharacterBinding.bind(itemView)

        fun bind(character: CharacterDomain, onItemClick: (CharacterDomain) -> Unit, onFavouriteClick: (CharacterDomain) -> Unit) {
            Glide.with(binding.characterPicture.context).load(character.image)
                .into(binding.characterPicture)
            binding.characterNameLabel.text = character.name
            setIsFavourite(character.isFavourite)

            binding.characterInfoContainer.setOnClickListener { onItemClick(character) }

            binding.favouriteImageView.setOnClickListener {
                character.isFavourite = !character.isFavourite
                onFavouriteClick(character)
                setIsFavourite(character.isFavourite)
            }
        }

        private fun setIsFavourite(isFavourite: Boolean){
            if(isFavourite) binding.favouriteImageView.setImageDrawable(AppCompatResources.getDrawable(binding.favouriteImageView.context, R.drawable.ic_favourite_on))
            else binding.favouriteImageView.setImageDrawable(AppCompatResources.getDrawable(binding.favouriteImageView.context, R.drawable.ic_favourite_off))
        }
    }
}