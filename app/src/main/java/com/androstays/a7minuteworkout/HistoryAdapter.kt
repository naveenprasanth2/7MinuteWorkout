package com.androstays.a7minuteworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.androstays.a7minuteworkout.databinding.ItemHisBinding
import com.androstays.a7minuteworkout.databinding.ItemHistoryRowBinding

class HistoryAdapter(private val items: ArrayList<HistoryEntity>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemHistoryRowBinding): RecyclerView.ViewHolder(binding.root){
        val llHistoryItemMain = binding.llHistoryItemMain
        val tvItem = binding.tvItem
        val tvPosition = binding.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date: String = items[position].date
        holder.tvPosition.text = (position+1).toString()
        holder.tvItem.text = date

        if(position%2 == 0){
            holder.llHistoryItemMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.colorGrey))
        }else{
            holder.llHistoryItemMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}