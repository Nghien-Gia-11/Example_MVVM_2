package com.example.example_mvvm_2.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example_mvvm_2.databinding.LayoutTextSuggestBinding

class SuggestAdapter(private var suggest : String) : RecyclerView.Adapter<SuggestAdapter.ViewHolder>() {

    private lateinit var binding : LayoutTextSuggestBinding

    inner class ViewHolder(binding : LayoutTextSuggestBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        binding = LayoutTextSuggestBinding.inflate(view, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = 16

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        for (i in 0 .. 16){
            if (suggest.length < 16){
                suggest += ('a'..'z').random()
            }
            else{
                break
            }
        }
        holder.itemView.apply {
            binding.txtTextSuggest.text = suggest[position].toString()
        }
    }

}