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
    private var check = false // 시작 버튼을 눌렀는지 안눌렀는지 체크하는 변수 눌렸다면 true
    private var time = 0 // 시간 변수 time의 1 당 0.01초
    private var timerTask : Timer? = null //타이머 함수
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

        startbtn.setOnClickListener{ //시작 버튼을 눌렀을 시에...
            if(!check) { // 시작버튼이 안눌려있는 상태라면
                startTimer() //함수 실행
            }
        }
        stopbtn.setOnClickListener { //정지 버튼을 눌렀을 시에...
            if(check) { // 시작버튼이 눌러진 상태라면
                stopTimer() //함수 실행
            }
        }
        resetbtn.setOnClickListener{ //리셋 버튼을 눌렀을 시에...
            resetTimer() //함수 실행
        }
        recordbtn.setOnClickListener{ //기록 버튼을 눌렀을 시에...
            var sec = time / 100 //초 = time / 100 (100으로 나눈 것의 몫)
            var milli = time % 100 //밀리초 = time % 100 (100으로 나눈 것의 나머지)
            var min = time / 6000 //분 = time / 6000 (6000으로 나눈 것의 몫)
            list_tv.text = "${list_tv.text.toString()}\n$min : $sec : $milli" //텍스트뷰에 시간을 출력하기 위한 코드
        }

    }



    private fun startTimer(){
        check = true //시작 버튼이 눌린 상태로 변경
        timerTask = timer(period = 10){ //타이머를 시작 (period = 10은 0.01초마다 반복
            time++ //0.01초마다 time +1

            var sec = time / 100 //초 = time / 100 (100으로 나눈 것의 몫)
            var milli = time % 100 //밀리초 = time % 100 (100으로 나눈 것의 나머지)
            var min = time / 6000 //분 = time / 6000 (6000으로 나눈 것의 몫)
            var empty = if(milli < 10) "0" //출력할 때 밀리초가 1의 자리일때랑 10의 자리일 때 길이가 달라서 1의 자리일 때는 뒤에 0을 붙이기 위함...
            else ""

            runOnUiThread{ //UI를 건드리기 위해서 써야하는 쓰레드..
                timetv!!.text = "$min : $sec : $milli$empty" //시간을 보여주는 텍스트에 시간 출력
            }
        }
    }
    private fun stopTimer(){
        check = false //시작 버튼이 안 눌린 상태로 변경
        timerTask?.cancel() //아까 시작한 타이머 정지..
    }
    private fun resetTimer(){
        if(check) { //시작 버튼이 눌려있는 상태라면..
            check = false //시작 버튼이 안 눌린 상태로 변경..
            timerTask?.cancel() //타이머 정지..
        }
        findViewById<TextView>(R.id.list_tv).text = "" //기록한 텍스트들 초기화..
        time = 0 //시간 초기화..
        timetv!!.text = "0 : 0 : 00" //시간을 보여주는 텍스트 초기화...
    }
}