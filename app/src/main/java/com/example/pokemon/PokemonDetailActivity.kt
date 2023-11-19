package com.example.pokemon

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pokemon.models.Pokemon
import com.google.android.material.chip.Chip

class PokemonDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        val viewBackground: View = findViewById(R.id.vw_background)
        val imgBack: ImageView = findViewById(R.id.btn_back)
        val imgPokemon: ImageView = findViewById(R.id.img_pokemon_photo)
        val tvName: TextView = findViewById(R.id.tv_pokemon_name)
        val tvNumber: TextView = findViewById(R.id.tv_pokemon_number)
        val tvAbout: TextView = findViewById(R.id.tv_about)
        val tvDescription: TextView = findViewById(R.id.tv_description)
        val chipType: Chip = findViewById(R.id.chip_pokemon_type)

        val pokemon = if(Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Pokemon>("pokemon", Pokemon::class.java)
        }else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Pokemon>("pokemon")
        }

        viewBackground.setBackgroundColor(pokemon?.color ?: resources.getColor(R.color.ground))

        imgBack.setOnClickListener {
            onBackPressed()
        }

        tvName.text = pokemon?.name ?: "Unknown"
        Glide.with(baseContext)
            .load(pokemon?.photo ?: "")
            .into(imgPokemon)
        tvNumber.text = pokemon?.number ?: "#000"
        tvAbout.setTextColor(pokemon?.color ?: resources.getColor(R.color.ground))
        tvDescription.text = pokemon?.description ?: "Unknown"
        chipType.text = pokemon?.type ?: ""

        chipType.chipBackgroundColor = ColorStateList.valueOf(pokemon?.color ?: resources.getColor(R.color.ground))
    }
}