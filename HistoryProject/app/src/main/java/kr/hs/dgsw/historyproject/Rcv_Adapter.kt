package kr.hs.dgsw.historyproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.historyproject.databinding.RcvItemBinding

class Rcv_Adapter(private val context: Context) : RecyclerView.Adapter<Rcv_Adapter.ViewHolder>() {

    var data = listOf<Rcv_Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RcvItemBinding.inflate(
            LayoutInflater.from(context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    inner class ViewHolder(val binding: RcvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Rcv_Item) {
            binding.item = data
            itemView.setOnClickListener {
//                val intent = Intent(context, Detail_Suggestion::class.java).apply {
//                    putExtra("data", data)
//                }.run { context.startActivity(this) }
            }

        }
    }


}