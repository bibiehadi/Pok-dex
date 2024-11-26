package com.example.pokemon

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.models.Pokemon
import com.google.android.material.chip.Chip

class PokemonDetailActivity : AppCompatActivity() {
    private lateinit var rvSkills: RecyclerView
    private var listSkill = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        val viewBackground: View = findViewById(R.id.vw_background)
        val imgBack: ImageView = findViewById(R.id.btn_back)
        val imgPokemon: ImageView = findViewById(R.id.img_pokemon_photo)
        val tvName: TextView = findViewById(R.id.tv_pokemon_name)
        val tvNumber: TextView = findViewById(R.id.tv_pokemon_number)
        val tvAbout: TextView = findViewById(R.id.tv_about)
        val tvSkill: TextView = findViewById(R.id.tv_skills)
        val tvDescription: TextView = findViewById(R.id.tv_description)
        val chipType: Chip = findViewById(R.id.chip_pokemon_type)
        val imgShare: ImageView = findViewById(R.id.img_action_share)
        rvSkills = findViewById(R.id.rv_pokemon_skills)


        val pokemon = if(Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Pokemon>("pokemon", Pokemon::class.java)
        }else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Pokemon>("pokemon")
        }

        listSkill = pokemon?.skill?.split(',')?.map {
            it.trim()
        }?.toCollection(ArrayList()) ?: arrayListOf()
        Log.d("Pokemon LIST SKILL", listSkill.toString())
        viewBackground.setBackgroundColor(pokemon?.color ?: resources.getColor(R.color.ground))

        imgBack.setOnClickListener {
            onBackPressed()
        }

        imgShare.setOnClickListener{
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my favorite Pokemon, ${pokemon?.name ?: "Unknown"}")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(intent, null)
            startActivity(shareIntent)
        }

        tvName.text = pokemon?.name ?: "Unknown"
        Glide.with(baseContext)
            .load(pokemon?.photo ?: "")
            .into(imgPokemon)
        tvNumber.text = pokemon?.number ?: "#000"
        tvAbout.setTextColor(pokemon?.color ?: resources.getColor(R.color.ground))
        tvSkill.setTextColor(pokemon?.color ?: resources.getColor(R.color.ground))
        tvDescription.text = pokemon?.description ?: "Unknown"
        chipType.text = pokemon?.type ?: ""

        chipType.chipBackgroundColor = ColorStateList.valueOf(pokemon?.color ?: resources.getColor(R.color.ground))

        if (pokemon != null) {
            Log.d("Pokemon",pokemon.typeImage)
            showRecyclerList(pokemon.typeImage)
        }
    }


    private fun showRecyclerList(imageSkill: String){
        rvSkills.layoutManager = LinearLayoutManager(this)
        val listSkillAdapter = ListPokemonSkillsAdapter(listSkill, imageSkill)
        rvSkills.adapter = listSkillAdapter
    }
}
