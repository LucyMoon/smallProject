package com.example.roomexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    lateinit var mAdapter : CatAdapter

    companion object{
        private var instance:MainActivity? = null
        var catList = arrayListOf<Cat>() //전역 변수로 선언
        var catDb : CatDB? = null //전역 변수로 선언
        fun getInstance(): MainActivity? {
            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mAddBtn = findViewById<Button>(R.id.mAddBtn)
        var mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)

        catDb = CatDB.getInstance(this)

        val savedcat = catDb!!.catDao().getAll()
        if(savedcat.isNotEmpty()){
            catList.addAll(savedcat)
        }

        mAdapter = CatAdapter(this, catList)

        val r = Runnable {
            try {
                catList = (catDb?.catDao()?.getAll() as ArrayList<Cat>)
                mAdapter = CatAdapter(this, catList)
                mAdapter.notifyDataSetChanged()

                mRecyclerView.adapter = mAdapter
                mRecyclerView.layoutManager = LinearLayoutManager(this)
                mRecyclerView.setHasFixedSize(true)
            } catch (e: Exception) {
                Log.d("tag", "Error - $e")
            }
        }

        val thread = Thread(r)
        thread.start()


        mAddBtn.setOnClickListener {
            val i = Intent(this, AddActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onDestroy() {
        CatDB.destroyInstance()
        catDb = null
        super.onDestroy()
    }

    fun del(position : Int){
        Log.d("tag", "$catList")
        catDb?.catDao()?.delete(cat = catList[position])
    }
}