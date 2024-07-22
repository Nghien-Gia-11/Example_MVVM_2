package com.example.example_mvvm_2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.indices
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.example_mvvm_2.Adapter.AnswerAdapter
import com.example.example_mvvm_2.Adapter.SuggestAdapter
import com.example.example_mvvm_2.Model.Question
import com.example.example_mvvm_2.OnClickRecycler.OnClick
import com.example.example_mvvm_2.ViewModel.MainViewModel
import com.example.example_mvvm_2.databinding.ActivityMainBinding
import com.example.example_mvvm_2.databinding.LayoutTextSuggestBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var listPic = mutableListOf<Question>()
    private val viewModel: MainViewModel by viewModels()
    private lateinit var suggestAdapter: SuggestAdapter
    private lateinit var answerAdapter: AnswerAdapter
    private lateinit var question: Question
    private lateinit var suggest: String
    private val answer = mutableListOf<String>()
    private lateinit var idPic: String
    private var check = ""
    private var score = 0
    private var heart = 5

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.listPicture.observe(this) { item ->
            listPic = item.toMutableList()
            randomQuestion()
            setAdapterAnswer()
        }

        binding.imgBgNext.setOnClickListener {
            randomQuestion()
        }
    }

    private fun randomQuestion() {
        question = listPic.random()
        suggest = question.answer
        idPic = suggest
        binding.imgQuestion.setImageResource(question.picture)
        setAdapterTextSuggest()
    }

    private fun setAdapterTextSuggest() {
        textSuggest()
        binding.recyclerTextSuggest.apply {
            adapter = SuggestAdapter(suggest, object : OnClick {
                @SuppressLint("NotifyDataSetChanged")
                override fun onClick(pos: Int) {
                    answer.add(suggest[pos].toString())
                    answerAdapter.notifyDataSetChanged()
                    if (answer.size == idPic.length) {
                        for (i in answer.indices) {
                            check += answer[i]
                        }
                        if (check == idPic) {
                            answerAdapter.setBackgroundChanged(2)
                            score += 100
                            binding.txtScore.text = score.toString()
                            binding.imgBgNext.visibility = View.VISIBLE
                            binding.txtNext.visibility = View.VISIBLE
                            answer.clear()
                            answerAdapter.notifyDataSetChanged()
                        } else {
                            answerAdapter.setBackgroundChanged(3)
                            heart -= 1
                            binding.txtHeart.text = heart.toString()
                            answer.clear()
                            answerAdapter.notifyDataSetChanged()
                        }
                    }
                }
            })
            layoutManager = GridLayoutManager(this@MainActivity, 8)
        }
    }

    private fun setAdapterAnswer() {
        answerAdapter = AnswerAdapter(idPic.length, answer)
        binding.recyclerTextAnswer.apply {
            adapter = answerAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 5)
        }
    }

    private fun textSuggest() {
        for (i in 0..16) {
            if (suggest.length < 16) {
                suggest = setTextRandom(('a'..'z').random())
            } else {
                break
            }
        }
    }

    private fun setTextRandom(charter: Char): String {
        val randomIndex = Random.nextInt(0, suggest.length + 1)
        val first = suggest.substring(0, randomIndex)
        val second = suggest.substring(randomIndex)
        return first + charter + second
    }
}