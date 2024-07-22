package com.example.example_mvvm_2.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example_mvvm_2.databinding.LayoutTextAnswerBinding

class AnswerAdapter(private val answer : String) : RecyclerView.Adapter<AnswerAdapter.ViewHolder>(){

    private lateinit var binding : LayoutTextAnswerBinding

    inner class ViewHolder(binding : LayoutTextAnswerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        binding = LayoutTextAnswerBinding.inflate(view, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = 8

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            binding.txtTextAnswer.text = "H"
        }
    }


}