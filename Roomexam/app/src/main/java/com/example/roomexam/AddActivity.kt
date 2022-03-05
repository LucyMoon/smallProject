package com.example.roomexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.concurrent.DelayQueue

class AddActivity : AppCompatActivity() {

    private var catDb : CatDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        var addName = findViewById<EditText>(R.id.addname)
        var addLifeSpan = findViewById<EditText>(R.id.addLifeSpan)
        var addOrigin = findViewById<EditText>(R.id.addOrigin)
        var addBtn = findViewById<Button>(R.id.addBtn)

        catDb = CatDB.getInstance(this)

        /* 새로운 cat 객체를 생성, id 이외의 값을 지정 후 DB에 추가 */
        val addRunnable = Runnable {
            val newCat = Cat()
            newCat.catName = addName.text.toString()
            newCat.lifeSpan = addLifeSpan.text.toString().toInt()
            newCat.origin = addOrigin.text.toString()
            catDb?.catDao()?.insert(newCat)
        }

        addBtn.setOnClickListener {
            val addThread = Thread(addRunnable)
            addThread.start()

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onDestroy() {
        CatDB.destroyInstance()
        super.onDestroy()
    }
}