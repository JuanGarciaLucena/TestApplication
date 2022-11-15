package com.juanlucena.testapplication.presentation.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanlucena.testapplication.data.database.entity.CharacterEntity
import com.juanlucena.testapplication.databinding.ActivityMainBinding
import com.juanlucena.testapplication.domain.model.CharacterDomain
import com.juanlucena.testapplication.presentation.adapters.CharacterAdapter
import com.juanlucena.testapplication.presentation.viewmodel.CharacterViewModel
import com.juanlucena.testapplication.utils.Constants.Companion.CHARACTER_DOMAIN
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterAdapter
    private val characterList = mutableListOf<CharacterDomain>()
    private val characterViewModel : CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initView()
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    private fun initView(){
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager(this).orientation)
        binding.characterRecyclerView.setHasFixedSize(false)
        binding.characterRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.characterRecyclerView.addItemDecoration(dividerItemDecoration)
        adapter = CharacterAdapter(
            characterList = characterList,
            onItemClick = {characterDomain -> onItemClick(characterDomain)},
            onFavouriteClick = {characterDomain -> onFavouriteClick(characterDomain) })
        binding.characterRecyclerView.adapter = adapter

    }

    private fun initData(){

        characterViewModel.getCharacters()
        characterViewModel.characterListResult.observe(this){ characterList ->
            this.characterList.clear()
            this.characterList.addAll(characterList)
            adapter.notifyDataSetChanged()
        }
    }

    private fun onItemClick(characterDomain: CharacterDomain){
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra(CHARACTER_DOMAIN, characterDomain)
        startActivity(intent)
    }

    private fun onFavouriteClick(characterDomain: CharacterDomain){
        characterViewModel.isFavourite(characterDomain.isFavourite, characterDomain.id)
    }
}