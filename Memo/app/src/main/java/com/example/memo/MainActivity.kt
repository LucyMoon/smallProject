package com.example.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var mAdapter : MemoAdapter

    companion object{
        private var instance:MainActivity? = null
        var memoList = arrayListOf<Memo>() //전역 변수로 선언
        var memoDb : MemoDB? = null //전역 변수로 선언
        var P : Int? = null
        fun getInstance(): MainActivity? {
            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var addbtn = findViewById<Button>(R.id.add_btn)
        var mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)

        memoDb = MemoDB.getInstance(this)

        val savedmemo = memoDb!!.memoDao().getAll()
        if(savedmemo.isNotEmpty()){
            memoList.addAll(savedmemo)
        }

        mAdapter = MemoAdapter(this, memoList)

        val r = Runnable {
            try {
                memoList = (memoDb?.memoDao()?.getAll() as ArrayList<Memo>)
                mAdapter = MemoAdapter(this, memoList)
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


        addbtn.setOnClickListener {
            val i = Intent(this, AddActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onDestroy() {
        MemoDB.destroyInstance()
        memoDb = null
        super.onDestroy()
    }

    fun del(position : Int){
        Log.d("tag", "$memoList")
        memoDb?.memoDao()?.delete(memo = memoList[position])
    }

    fun pos(pos: Int){
        P = pos
        //startActivity(Intent(this, modifyActivity::class.java))
        //finish()
    }

    fun mod(title : String , text : String){
        memoDb?.memoDao()?.update(title, text, memoList[P!!].id)
        memoList.clear()
        val savedmemo = memoDb!!.memoDao().getAll()
        if(savedmemo.isNotEmpty()){
            memoList.addAll(savedmemo)
        }
    }
}