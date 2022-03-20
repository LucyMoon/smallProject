package com.example.animationexam

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val img_icon = findViewById<ImageView>(R.id.img_icon)

        img_icon.setOnClickListener {
            if (img_icon.tag == null){
                img_icon.setImageResource(R.drawable.anim_vector_play_to_pause)
                (img_icon.drawable as AnimatedVectorDrawable).start()
                img_icon.tag = 0
            }
            else{
                img_icon.setImageResource(R.drawable.anim_vector_pause_to_play)
                (img_icon.drawable as AnimatedVectorDrawable).start()
                img_icon.tag = null
            }

        }

    }
}