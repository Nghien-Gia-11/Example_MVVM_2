package com.example.example_mvvm_2.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example_mvvm_2.databinding.LayoutTextSuggestBinding
import kotlin.random.Random

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
        textSuggest()
        holder.itemView.apply {
            binding.txtTextSuggest.text = suggest[position].toString()
        }
    }

    private fun textSuggest(){
        for (i in 0 .. 16){
            if (suggest.length < 16){
                suggest = setTextRandom(('a'..'z').random())
            }
            else{
                break
            }
        }
    }

    private fun setTextRandom(charter : Char) : String{
        val randomIndex = Random.nextInt(0, suggest.length + 1)
        val first = suggest.substring(0, randomIndex)
        val second = suggest.substring(randomIndex)
        return first + charter + second
    }
}