package com.example.pokemon

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
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
        rvPokemons.setHasFixedSize(true)

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
                "electric" -> resources.getColor(R.color.electric)
                "psychic" -> resources.getColor(R.color.psychic)
                "grass" -> resources.getColor(R.color.grass)
                "normal" -> resources.getColor(R.color.steel)
                "ice" -> resources.getColor(R.color.ice)
                "fairy" -> resources.getColor(R.color.fairy)
                "water" -> resources.getColor(R.color.water)
                "bug" -> resources.getColor(R.color.grass2)
                "ghost" -> resources.getColor(R.color.steel)
                "steel" -> resources.getColor(R.color.steel)
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
//        Toast.makeText(this, "Kamu memilih " + pokemon.name, Toast.LENGTH_SHORT).show()
        val moveIntent = Intent(this@MainActivity, PokemonDetailActivity::class.java)
        moveIntent.putExtra("pokemon", pokemon)
        startActivity(moveIntent)
    }


    private fun showMenu(view: View){
        PopupMenu(this, view).apply {
            setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
             onMenuItemClickListener(it)
            })
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                setForceShowIcon(true)
            }
            inflate(R.menu.menu_main)
            show()
        }
    }

     private fun onMenuItemClickListener(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvPokemons.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvPokemons.layoutManager = GridLayoutManager(this,2)
            }
            R.id.action_about_page -> {
                val moveIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}