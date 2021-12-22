package com.example.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
    }
    private fun loadData() {
        val ETV = findViewById<EditText>(R.id.main_ETV)
        var pref = getSharedPreferences("pref", 0)
        ETV.setText(pref.getString("name", "")) //1번째 인자에서는 저장한 당시에 키 값을 적어주고, 2번째는 키 값에 데이터가 존재하지 않을 경우 대체 값을 적어준다
    }

    private fun saveData() {
        val ETV = findViewById<EditText>(R.id.main_ETV)
        var pref = getSharedPreferences("pref", 0)
        val edit = pref.edit() //수정 모드
        edit.putString("name", ETV.text.toString())//1번째 인자에는 키값, 두번째 인자에는 실제 담아둘 값
        edit.apply() //값을 저장 완료
    }

    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }
}