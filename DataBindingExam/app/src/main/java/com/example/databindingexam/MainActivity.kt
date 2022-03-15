package com.example.databindingexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databindingexam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.activity = this@MainActivity
        setRcv()
    }

    fun btnClick(){
        Toast.makeText(this, "Button CLick", Toast.LENGTH_SHORT).show()
        setObserv(binding.ET.text.toString())
    }
    fun setRcv(){
        val profileAdapter = ProfileAdapter(this)
        binding.mainRcv.layoutManager = LinearLayoutManager(this)
        binding.mainRcv.adapter = profileAdapter
        profileAdapter.data = listOf(
            ProfileData("https://i.ibb.co/brXQ5qp/ball1.png", name = "Kang", age = 26),
            ProfileData("https://i.ibb.co/brXQ5qp/ball1.png", name = "Kim", age = 25)
        )
        profileAdapter.notifyDataSetChanged()
    }
    fun setObserv(text : String){
        var item = ObservableData()
        item.site = text
        binding.site = item
    }
}