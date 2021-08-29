package com.example.kpm.ui.promo.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kpm.databinding.PromoItemBinding
import com.example.kpm.model.Promo

class PromoAdapter(val promoClickListener: PromoClickListener ): RecyclerView.Adapter<PromoAdapter.PromoViewHolder>() {
    var promos = ArrayList<Promo>()
    inner class PromoViewHolder(val binding: PromoItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PromoViewHolder {
        val binding = PromoItemBinding.inflate(LayoutInflater.from(parent.context))
        return PromoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.binding.promoName.text = promos[position].name
        holder.binding.promoText.text = promos[position].text
        holder.binding.promoButton.text = promos[position].button_text
//        holder.binding.euroAvailableText.text = promos[position].euro_available.toString()
        Glide.with(holder.itemView.context)
            .load(promos[position].image)
            .into(holder.binding.promoImage)
//        holder.binding.promoButton.setTextColor(promos[position].button_color)

        holder.binding.promoButton.setOnClickListener{
            promoClickListener.onPromoButtonClicked(promos[position].link)
        }
    }

    override fun getItemCount(): Int {
        return promos.size
    }


    fun setData(arrayList: ArrayList<Promo>){
        promos = arrayList
        notifyDataSetChanged()
    }

    interface PromoClickListener{
        fun onPromoButtonClicked(link: String)
    }
}