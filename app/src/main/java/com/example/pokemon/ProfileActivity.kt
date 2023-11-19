package com.example.pokemon

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val imgProfile : ImageView = findViewById(R.id.img_profile)
        val imgBack : ImageView = findViewById(R.id.btn_back)

        Glide.with(baseContext)
            .load("https://avatars.githubusercontent.com/u/29392494?v=4")
            .circleCrop()
            .into(imgProfile)

        imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}