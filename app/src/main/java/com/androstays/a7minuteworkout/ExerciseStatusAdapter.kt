package com.androstays.a7minuteworkout

import androidx.recyclerview.widget.RecyclerView
import com.androstays.a7minuteworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>):
RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {
    class ViewHolder(binding:ItemExerciseStatusBinding):
        RecyclerView.ViewHolder(binding.root){
        val tvItem = binding.tvItem
    }
}