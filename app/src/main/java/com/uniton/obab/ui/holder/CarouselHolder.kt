package com.uniton.obab.ui.holder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.uniton.obab.R
import com.uniton.obab.databinding.HolderImgItemBinding


class CarouselHolder(
    private val binding: HolderImgItemBinding,
    private val glide: RequestManager,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imgResId: Int) = with(binding) {
        //var requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(16))
        //glide.load(imgResId).into(ivFood)

        ivFood.setImageResource(imgResId)
    }
}
