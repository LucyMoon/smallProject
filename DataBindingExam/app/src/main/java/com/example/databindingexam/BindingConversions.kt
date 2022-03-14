package com.example.databindingexam

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingConversions {

    @BindingAdapter("imageUrl", "error")
    @JvmStatic // "@JvmStatic"은 Static 함수로 설정해주기 위한 Annotation이다
    fun loadImage(imageView : ImageView, url : String, error : Drawable){

        Glide.with(imageView.context).load(url)
            .error(error)
            .into(imageView)
    }

}