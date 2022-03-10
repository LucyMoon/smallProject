package com.example.lotto_stu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    var num = IntArray(6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.start_btn)
        val ball1 = findViewById<ImageView>(R.id.ball_1)
        val ball2 = findViewById<ImageView>(R.id.ball_2)
        val ball3 = findViewById<ImageView>(R.id.ball_3)
        val ball4 = findViewById<ImageView>(R.id.ball_4)
        val ball5 = findViewById<ImageView>(R.id.ball_5)
        val ball6 = findViewById<ImageView>(R.id.ball_6)
        val balltv1 = findViewById<TextView>(R.id.ball_tv_1)
        val balltv2 = findViewById<TextView>(R.id.ball_tv_2)
        val balltv3 = findViewById<TextView>(R.id.ball_tv_3)
        val balltv4 = findViewById<TextView>(R.id.ball_tv_4)
        val balltv5 = findViewById<TextView>(R.id.ball_tv_5)
        val balltv6 = findViewById<TextView>(R.id.ball_tv_6)
        btn.setOnClickListener{
            for(i in 0..5){
                random(i)
            }
            balltv1.text = num[0].toString()
            balltv2.text = num[1].toString()
            balltv3.text = num[2].toString()
            balltv4.text = num[3].toString()
            balltv5.text = num[4].toString()
            balltv6.text = num[5].toString()

            ball1.setImageResource(
                when(num[0]){
                    in 1..10 -> R.drawable.ball1
                    in 11..20 -> R.drawable.ball2
                    in 21..30 -> R.drawable.ball3
                    in 31..40 -> R.drawable.ball4
                    in 41..45 -> R.drawable.ball5
                    else -> R.drawable.ball1
                }
            )
            ball2.setImageResource(
                when(num[1]){
                    in 1..10 -> R.drawable.ball1
                    in 11..20 -> R.drawable.ball2
                    in 21..30 -> R.drawable.ball3
                    in 31..40 -> R.drawable.ball4
                    in 41..45 -> R.drawable.ball5
                    else -> R.drawable.ball1
                }
            )
            ball3.setImageResource(
                when(num[2]){
                    in 1..10 -> R.drawable.ball1
                    in 11..20 -> R.drawable.ball2
                    in 21..30 -> R.drawable.ball3
                    in 31..40 -> R.drawable.ball4
                    in 41..45 -> R.drawable.ball5
                    else -> R.drawable.ball1
                }
            )
            ball4.setImageResource(
                when(num[3]){
                    in 1..10 -> R.drawable.ball1
                    in 11..20 -> R.drawable.ball2
                    in 21..30 -> R.drawable.ball3
                    in 31..40 -> R.drawable.ball4
                    in 41..45 -> R.drawable.ball5
                    else -> R.drawable.ball1
                }
            )
            ball5.setImageResource(
                when(num[4]){
                    in 1..10 -> R.drawable.ball1
                    in 11..20 -> R.drawable.ball2
                    in 21..30 -> R.drawable.ball3
                    in 31..40 -> R.drawable.ball4
                    in 41..45 -> R.drawable.ball5
                    else -> R.drawable.ball1
                }
            )
            ball6.setImageResource(
                when(num[5]){
                    in 1..10 -> R.drawable.ball1
                    in 11..20 -> R.drawable.ball2
                    in 21..30 -> R.drawable.ball3
                    in 31..40 -> R.drawable.ball4
                    in 41..45 -> R.drawable.ball5
                    else -> R.drawable.ball1
                }
            )
        }
    }

    fun random(cnt : Int){
        val Rnum : Int
        var A = 0
        Rnum = Random().nextInt(45) + 1
        for(i in 0..cnt){
            if(num[i] == Rnum){
                A++
                break
            }
        }
        if(A==0){
            num[cnt] = Rnum
        }
        else{
            random(cnt)
        }
    }
}