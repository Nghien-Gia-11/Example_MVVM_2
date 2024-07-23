package com.example.example_mvvm_2.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example_mvvm_2.R
import com.example.example_mvvm_2.databinding.LayoutTextAnswerBinding

class AnswerAdapter(
    private val numberNode: Int,
    private val answer: List<String>,
) :
    RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {
    private var isChanged : Int = 1
    inner class ViewHolder(val binding: LayoutTextAnswerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = LayoutTextAnswerBinding.inflate(view, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = numberNode

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position >= answer.size) {
            holder.binding.txtTextAnswer.text = ""
        } else {
            holder.binding.txtTextAnswer.text = answer[position]
        }
        when(isChanged){
            1 -> holder.itemView.setBackgroundResource(R.drawable.ic_tile_answer)
            2 -> holder.itemView.setBackgroundResource(R.drawable.ic_tile_true)
            else -> holder.itemView.setBackgroundResource(R.drawable.ic_tile_false)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBackgroundChanged(changed: Int) {
        isChanged = changed
        notifyDataSetChanged()
    }
}