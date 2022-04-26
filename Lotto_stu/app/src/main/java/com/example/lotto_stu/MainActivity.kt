package com.example.lotto_stu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    var num = IntArray(6) //C언어에서 int num[6]과 같은 의미


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.start_btn) //btn이라는 변수에 xml에 있는 시작 버튼 id를 담았음
        val ball1 = findViewById<ImageView>(R.id.ball_1) //위와 동일
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

        btn.setOnClickListener{ //위에서 변수에 담은 시작버튼이 눌린다면...
            for(i in 0..5){ //C언에서 for 반복문 돌리는거랑 같음 0~5까지 돌린다는 의미
                random(i) //랜덤 숫자 뽑아내는 함수인데 이해할 필요 없음..
            }
            balltv1.text = num[0].toString() //위에서 변수에 담은 텍스트뷰(텍스트를 보여주는 것)의 텍스트를 아까 랜덤숫자 뽑는 함수에서 뽑은 값을 넣어준다 .toString은 int를 문자열로 변환하는 것
            balltv2.text = num[1].toString()
            balltv3.text = num[2].toString()
            balltv4.text = num[3].toString()
            balltv5.text = num[4].toString()
            balltv6.text = num[5].toString()
            ball1.setImageResource( //ball1은 위에서 동그란 공의 아이디를 담은 변수 즉, 동그란 공의 이미지를 설정해주는 것
                when(num[0]){ //C언어에서 switch문과 같음
                    in 1..10 -> R.drawable.ball1 //랜덤 숫자 뽑기에서 1~10이 걸렸다면.. 이 이미지를 설정해주겠다..
                    in 11..20 -> R.drawable.ball2 //11~20이라면..
                    in 21..30 -> R.drawable.ball3 //~
                    in 31..40 -> R.drawable.ball4 //~
                    in 41..45 -> R.drawable.ball5 //~
                    else -> R.drawable.ball1 //만약 1~45의 범주에서 벗어난다면(오류) 기본값 설정
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

    fun random(cnt : Int){ //여기 아래서부터는 이해 안해도 됨
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