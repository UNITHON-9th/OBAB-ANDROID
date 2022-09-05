package com.uniton.obab.ui.adaper

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.uniton.obab.databinding.HolderImgItemBinding
import com.uniton.obab.ui.holder.CarouselHolder

class CarouselAdapter(
    private val context: Context,
    private val initItems: List<Int>
) : RecyclerView.Adapter<CarouselHolder>() {
    private val glide: RequestManager by lazy { Glide.with(context) }
    private var items = initItems

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselHolder {
        return CarouselHolder(
            HolderImgItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            glide
        )
    }

    override fun onBindViewHolder(holder: CarouselHolder, position: Int) {
        holder.bind(items[position % items.count()])
    }

    override fun getItemCount(): Int = Int.MAX_VALUE
}
