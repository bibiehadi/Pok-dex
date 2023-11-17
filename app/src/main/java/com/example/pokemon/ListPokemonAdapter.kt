package com.example.pokemon

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.models.Pokemon
import com.google.android.material.chip.Chip

class ListPokemonAdapter(private val listPokemon: ArrayList<Pokemon>): RecyclerView.Adapter<ListPokemonAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imgPokemon: ImageView = itemView.findViewById(R.id.img_pokemon_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_pokemon_name)
        val tvNumber: TextView = itemView.findViewById(R.id.tv_pokemon_number)
        val chipType: Chip = itemView.findViewById(R.id.chip_pokemon_type)
        val cardPokemon: ConstraintLayout = itemView.findViewById(R.id.card_pokemon)

    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_pokemon, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPokemon.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, number, type, photo, backgroundColor) = listPokemon[position]
//        holder.imgPokemon.setImageResource()
        holder.tvName.text = name
        holder.tvNumber.text = number
        holder.chipType.text = type

        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(listPokemon[holder.adapterPosition])}
        holder.chipType.chipBackgroundColor = ColorStateList.valueOf(backgroundColor)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Pokemon)
    }

}


