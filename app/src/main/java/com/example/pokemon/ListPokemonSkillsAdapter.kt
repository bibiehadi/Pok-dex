package com.example.pokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListPokemonSkillsAdapter(private val listSkill: ArrayList<String>, private val imageSkill: String): RecyclerView.Adapter<ListPokemonSkillsAdapter.ListViewHolder>(){

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {
        val imgSkill: ImageView = itemView.findViewById(R.id.img_pokemon_skill)
        val tvSkill: TextView = itemView.findViewById(R.id.tv_pokemon_skill)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_pokemon_skill, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listSkill.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(imageSkill)
            .into(holder.imgSkill)

        holder.tvSkill.text = listSkill[position]
    }

}