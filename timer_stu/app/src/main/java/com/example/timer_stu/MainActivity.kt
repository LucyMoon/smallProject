package com.example.timer_stu

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    private var check = false
    private var time = 0
    private var timerTask : Timer? = null
    private var timetv : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timetv = findViewById(R.id.time_tv)
        val recordbtn = findViewById<Button>(R.id.record)
        val startbtn = findViewById<Button>(R.id.start)
        val stopbtn = findViewById<Button>(R.id.stop)
        val resetbtn = findViewById<ImageView>(R.id.refresh)
        val list_tv = findViewById<TextView>(R.id.list_tv)

        startbtn.setOnClickListener{
            if(!check) {
                startTimer()
            }
        }
        stopbtn.setOnClickListener {
            if(check) {
                stopTimer()
            }
        }
        resetbtn.setOnClickListener{
            resetTimer()
        }
        recordbtn.setOnClickListener{
            var sec = time / 100
            var milli = time % 100
            var min = time / 6000
            list_tv.text = "${list_tv.text.toString()}\n$min : $sec : $milli"
        }

    }

    private fun startTimer(){
        check = true
        timerTask = timer(period = 10){
            time++

            var sec = time / 100
            var milli = time % 100
            var min = time / 6000
            var empty = if(milli < 10) "0"
            else ""

            runOnUiThread{
                timetv!!.text = "$min : $sec : $milli$empty"
            }
        }
    }
    private fun stopTimer(){
        check = false
        timerTask?.cancel()
    }
    private fun resetTimer(){
        if(check) {
            check = false
            timerTask?.cancel()
        }
        findViewById<TextView>(R.id.list_tv).text = ""
        time = 0
        timetv!!.text = "0 : 0 : 00"
    }
}