package com.example.pokemon

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.models.Pokemon

class MainActivity : AppCompatActivity() {
    private lateinit var rvPokemons: RecyclerView
    private var list = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPokemons = findViewById(R.id.rv_pokemons)
        rvPokemons.setHasFixedSize(true)

        list.addAll(getListPokemons())
        showRecyclerList()
    }

    private fun getListPokemons(): ArrayList<Pokemon> {
        val dataName = resources.getStringArray(R.array.pokemon_name)
        val dataDescription = resources.getStringArray(R.array.pokemon_description)
        val dataPhoto = resources.getStringArray(R.array.pokemon_photo)
        val dataType = resources.getStringArray(R.array.pokemon_type)
        val dataNumber = resources.getStringArray(R.array.pokemon_number)
        val listPokemon = ArrayList<Pokemon>()
        for(i in dataName.indices) {
            val color = when (dataType[i]) {
                "fire" -> resources.getColor(R.color.fire)
                "electric" -> resources.getColor(R.color.electric2)
                else -> resources.getColor(R.color.ground)
            }
            val pokemon = Pokemon(
                dataName[i] ?: "",
                dataNumber[i] ?: "0",
                dataType[i] ?: "",
                dataDescription[i] ?: "",
                color,
            )
            listPokemon.add(pokemon)
        }
        return listPokemon
    }

    private fun showRecyclerList(){
        rvPokemons.layoutManager = LinearLayoutManager(this)
        val listPokemonAdapter = ListPokemonAdapter(list)
        rvPokemons.adapter = listPokemonAdapter

        listPokemonAdapter.setOnItemClickCallback(object : ListPokemonAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Pokemon) {
                showSelectedPokemon(data)
            }
        })
    }

    private fun showSelectedPokemon(pokemon: Pokemon){
        Toast.makeText(this, "Kamu memilih " + pokemon.name, Toast.LENGTH_SHORT).show()
    }
}