package com.example.example_mvvm_2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example_mvvm_2.OnClickRecycler.OnClick
import com.example.example_mvvm_2.databinding.LayoutTextSuggestBinding

class SuggestAdapter(private var suggest : String, private val onClick : OnClick) : RecyclerView.Adapter<SuggestAdapter.ViewHolder>() {

    private lateinit var binding : LayoutTextSuggestBinding
    private val visibilityList = MutableList(suggest.length) { true }
    inner class ViewHolder(binding : LayoutTextSuggestBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        binding = LayoutTextSuggestBinding.inflate(view, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = 16

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            binding.txtTextSuggest.text = suggest[position].toString()
            visibility = if (visibilityList[position]) View.VISIBLE else View.INVISIBLE
            setOnClickListener {
                onClick.onClick(position)
                visibilityList[position] = false
                notifyItemChanged(position)
            }
        }
    }

    private fun isUnCorrect(){
        for (i in visibilityList.indices){
            visibilityList[i] = true
        }
    }


}