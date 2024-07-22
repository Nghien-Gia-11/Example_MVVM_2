package com.example.example_mvvm_2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.example_mvvm_2.Adapter.AnswerAdapter
import com.example.example_mvvm_2.Adapter.SuggestAdapter
import com.example.example_mvvm_2.Model.Question
import com.example.example_mvvm_2.ViewModel.MainViewModel
import com.example.example_mvvm_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var listPic = mutableListOf<Question>()
    private val viewModel : MainViewModel by viewModels()

    private lateinit var suggestAdapter : SuggestAdapter
    private lateinit var answerAdapter : AnswerAdapter
    private lateinit var question : Question

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.listPicture.observe(this) { item ->
            listPic = item.toMutableList()
            randomQuestion()
        }

        binding.imgBgNext.setOnClickListener {
            randomQuestion()
        }
    }

    private fun randomQuestion(){
        question = listPic.random()
        binding.imgQuestion.setImageResource(question.picture)
        setAdapterTextSuggest()
    }

    private fun setAdapterTextSuggest(){
        binding.recyclerTextSuggest.apply {
            adapter  = SuggestAdapter(question.answer)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        }
    }
}