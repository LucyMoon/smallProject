package kr.hs.dgsw.historyproject

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingConversions {
    @BindingAdapter("imageUrl","error")
    @JvmStatic
    fun loadImage(imageView : ImageView, url : String, error : Drawable){

        Glide.with(imageView.context).load(url)
            .error(error)
            .into(imageView)
    }

}