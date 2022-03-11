package com.example.databindingexam

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingexam.databinding.RcvListItemBinding

class ProfileAdapter(private val context : Context) : RecyclerView.Adapter<ProfileAdapter.ProfileVH>() {

    var data = listOf<ProfileData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileVH {
        val binding = RcvListItemBinding.inflate(
            LayoutInflater.from(context), parent, false)

        return ProfileVH(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProfileVH, position: Int) {
        holder.onBind(data[position])
    }

    class ProfileVH(val binding: RcvListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ProfileData) {
            binding.user = data
        }
    }
}