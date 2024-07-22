package com.example.example_mvvm_2

import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.listPicture.observe(this) { item ->
            listPic = item.toMutableList()
        }

        binding.imgBgNext.setOnClickListener {
            val question = listPic.random()
            randomQuestion(question)
        }

        binding.recyclerTextSuggest.apply {
            adapter = SuggestAdapter("ssasd")
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        }

    }

    private fun randomQuestion(question : Question){
        Glide
            .with(this)
            .load(question.picture)
            .into(binding.imgQuestion)
    }


}