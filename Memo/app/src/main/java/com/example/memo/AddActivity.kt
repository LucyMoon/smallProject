package com.example.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddActivity : AppCompatActivity() {
    private var memoDb : MemoDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        var addtitle = findViewById<EditText>(R.id.mod_title)
        var addtext = findViewById<EditText>(R.id.mod_text)
        var addnext = findViewById<Button>(R.id.mod_next)

        memoDb = MemoDB.getInstance(this)

        /* 새로운 cat 객체를 생성, id 이외의 값을 지정 후 DB에 추가 */
        val addRunnable = Runnable {
            val newMemo = Memo()
            newMemo.title = addtitle.text.toString()
            newMemo.text = addtext.text.toString()
            memoDb?.memoDao()?.insert(newMemo)
        }

        addnext.setOnClickListener {
            val addThread = Thread(addRunnable)
            addThread.start()

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onDestroy() {
        MemoDB.destroyInstance()
        super.onDestroy()
    }
}