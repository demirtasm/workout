package com.example.a7minuteworkout.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.databinding.ItemExerciseStatusBinding
import com.example.a7minuteworkout.model.ExerciseModel

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemExerciseStatusBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvItem = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemExerciseStatusBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = items[position]
        holder.tvItem.text = model.id.toString()
        when {
            model.isSelected -> {
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circler_thin_color_accent_background
                )
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }

            model.isCompleted -> {
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circler_color_accent_background
                )
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }

            else -> {
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circler_color_gray_background
                )
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
        }
    }
}