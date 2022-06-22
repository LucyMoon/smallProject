package kr.hs.dgsw.historyproject

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.historyproject.databinding.BannerListItemBinding

class ViewPagerAdapter(private val context: Context) :
    RecyclerView.Adapter<ViewPagerAdapter.PagerVH>() {

    var data = listOf<BannerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        val binding = BannerListItemBinding.inflate(
            LayoutInflater.from(context), parent, false)

        return PagerVH(binding)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        holder.onBind(data[position % 3])
    }

    class PagerVH(val binding: BannerListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BannerData) {
            binding.banner = data
        }
    }
}