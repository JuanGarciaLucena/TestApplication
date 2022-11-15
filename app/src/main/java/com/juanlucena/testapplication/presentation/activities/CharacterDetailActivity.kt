package com.juanlucena.testapplication.presentation.activities

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.juanlucena.testapplication.R
import com.juanlucena.testapplication.databinding.ActivityCharacterDetailBinding
import com.juanlucena.testapplication.domain.model.CharacterDomain
import com.juanlucena.testapplication.presentation.viewmodel.CharacterViewModel
import com.juanlucena.testapplication.utils.Constants.Companion.CHARACTER_DOMAIN
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding
    private lateinit var character: CharacterDomain
    private val characterViewModel : CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initListeners()
    }

    private fun initView(){
        character = getSerializable(this, CHARACTER_DOMAIN, CharacterDomain::class.java)
        binding.characterNameLabel.text = character.name
        Glide.with(this).load(character.image).into(binding.characterPicture)
        setIsFavourite(character.isFavourite)
    }

    private fun initListeners(){

        binding.favouriteImageView.setOnClickListener {
           characterViewModel.isFavourite(!character.isFavourite, character.id)
        }

        characterViewModel.isFavouriteLiveData.observe(this){ isFavourite ->
            setIsFavourite(isFavourite)
        }
    }

    private fun setIsFavourite(isFavourite: Boolean){
        if(isFavourite)binding.favouriteImageView.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_favourite_on))
        else binding.favouriteImageView.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_favourite_off))
    }

    private fun <T : Serializable?> getSerializable(activity: Activity, name: String, clazz: Class<T>): T {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            activity.intent.getSerializableExtra(name, clazz)!!
        else
            activity.intent.getSerializableExtra(name) as T
    }
}