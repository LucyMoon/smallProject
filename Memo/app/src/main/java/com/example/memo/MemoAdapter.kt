package com.example.memo

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemoAdapter(val context: Context, val memos: ArrayList<Memo>) :
    RecyclerView.Adapter<MemoAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_memo, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return memos.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(memos[position])
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val title = itemView?.findViewById<TextView>(R.id.title_tv)
        val text = itemView?.findViewById<TextView>(R.id.text_tv)
        val modify = itemView?.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.modify_btn)
        val delete = itemView?.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.delete_btn)

        fun bind(memo: Memo) {
            title?.text = memo.title
            text?.text = memo.text
            text?.visibility = View.GONE
            modify!!.setOnClickListener{
                MainActivity().pos(adapterPosition)
                MainActivity().mod("aa", "aa")
                //위 코드를 액티비티 넘어간 뒤에 실행
                notifyDataSetChanged()
            }
            delete!!.setOnClickListener{
                MainActivity().del(adapterPosition)
                memos.removeAt(adapterPosition)
                notifyDataSetChanged()
            }
            itemView.setOnClickListener{
                if(text?.visibility == View.VISIBLE){
                    text?.visibility = View.GONE
                }else{
                    text?.visibility = View.VISIBLE
                }
            }
        }
    }
}