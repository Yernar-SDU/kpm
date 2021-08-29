package com.example.kpm.ui.archive.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kpm.databinding.ItemArchiveBinding
import com.example.kpm.model.Archive

class ArchiveAdapter: RecyclerView.Adapter<ArchiveAdapter.PromoViewHolder>() {
    var archives = ArrayList<Archive>()
    inner class PromoViewHolder(val binding: ItemArchiveBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val binding = ItemArchiveBinding.inflate(LayoutInflater.from(parent.context))
        return PromoViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.binding.actualTime.text ="Actual time: ${archives[position].ActualTime}"
        holder.binding.comment.text = "Comment: ${archives[position].Comment}"
        holder.binding.pair.text = "Pair: ${archives[position].Pair}"
        holder.binding.cmd.text = "Cmd: ${archives[position].Cmd}"
        holder.binding.tradingSystem.text ="Trading System: ${archives[position].TradingSystem}"
        holder.binding.period.text = "Period: ${archives[position].Period}"
        holder.binding.price.text = "Price: ${archives[position].Price}"
        holder.binding.SL.text = "SL: ${archives[position].SL}"
        holder.binding.Tp.text = "TP: ${archives[position].Tp}"

    }

    override fun getItemCount(): Int {
        return archives.size
    }


    fun setData(array: ArrayList<Archive>){
        archives = array
        notifyDataSetChanged()
    }
}