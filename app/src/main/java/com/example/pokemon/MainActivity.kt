package com.example.pokemon

import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.models.Pokemon

class MainActivity : AppCompatActivity() {
    private lateinit var rvPokemons: RecyclerView
    private lateinit var imgMenu: ImageView
    private var list = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imgMenu = findViewById(R.id.img_menu)

        rvPokemons = findViewById(R.id.rv_pokemons)
//        rvPokemons.setHasFixedSize(true)

        imgMenu.setOnClickListener {
            showMenu(it)
        }

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
                dataPhoto[i],
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


    private fun showPopup(view: View){
        val popup = PopupMenu(this, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_main, popup.menu)
        popup.show()
    }

    private fun showMenu(view: View){
        PopupMenu(this, view).apply {
            setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
             onMenuItemClickListener(it)
            })
            inflate(R.menu.menu_main)
            show()
        }
    }

     fun onMenuItemClickListener(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvPokemons.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvPokemons.layoutManager = GridLayoutManager(this,2)
            }
            R.id.action_profile -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}