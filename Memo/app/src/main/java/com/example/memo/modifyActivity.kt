package com.example.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class modifyActivity : AppCompatActivity() {
    val modtitle = findViewById<EditText>(R.id.mod_title)
    val modtext = findViewById<EditText>(R.id.mod_text)
    val modnext = findViewById<Button>(R.id.mod_next)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)

        /*modnext.setOnClickListener{
            MainActivity().mod(modtitle.text.toString(), modtext.text.toString())
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }*/

    }
}