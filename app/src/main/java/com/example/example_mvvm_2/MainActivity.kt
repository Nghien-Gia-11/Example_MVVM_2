package com.example.example_mvvm_2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.example_mvvm_2.Adapter.AnswerAdapter
import com.example.example_mvvm_2.Adapter.SuggestAdapter
import com.example.example_mvvm_2.Model.Question
import com.example.example_mvvm_2.OnClickRecycler.OnClick
import com.example.example_mvvm_2.ViewModel.MainViewModel
import com.example.example_mvvm_2.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private var listPic = mutableListOf<Question>()
    private lateinit var answerAdapter: AnswerAdapter
    private lateinit var question: Question
    private lateinit var suggest: String
    private val answer = mutableListOf<String>()
    private lateinit var idPic: String


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
            randomQuestion() // random câu hỏi
            answer.clear() // clear đáp án trước khi vào câu hỏi mới
            setAdapterAnswer()
            answerAdapter.setBackgroundChanged(1) // sửa lại màu nền của ô nhập đáp án là 1 nếu chưa nhập đủ đáp án
            answerAdapter.notifyDataSetChanged()
            inVisibleNext()
        }
    }

    private fun randomQuestion() {
        if (listPic.isNotEmpty()){
            question = listPic.random()
            listPic.remove(question)
        }else{
            Toast.makeText(this, "Bạn đã hoàn thành trò chơi !!", Toast.LENGTH_SHORT).show()
        }
        suggest = question.answer // lấy suggest là đáp án của câu hỏi
        idPic = suggest // đáp án của câu hỏi
        Log.e("TAG", idPic)
        binding.imgQuestion.setImageResource(question.picture)
        setAdapterTextSuggest()
    }

    private fun setAdapterTextSuggest() {
        textSuggest() // trộn các ký tự của câu hỏi và các ký tự random khác
        var check = ""
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
                            answerAdapter.setBackgroundChanged(2) // sửa lại màu nền của ô nhập đáp án là 2 nếu đúng
                            viewModel.getScore()
                            viewModel.score.observe(this@MainActivity) {
                                binding.txtScore.text = it.toString()
                            }
                            visibleNext()
                            answerAdapter.notifyDataSetChanged()
                            Toast.makeText(this@MainActivity, "Correct", Toast.LENGTH_SHORT).show()
                        } else {
                            answerAdapter.setBackgroundChanged(3) // sửa lại màu nền của ô nhập đáp án là 3 nếu sai
                            viewModel.getHeart()
                            viewModel.heart.observe(this@MainActivity) {
                                binding.txtHeart.text = it.toString()
                                if (it == 0){
                                    Toast.makeText(this@MainActivity, "bạn đã thua", Toast.LENGTH_LONG).show()
                                    finish()
                                }
                            }
                            visibleNext()
                            answerAdapter.notifyDataSetChanged()
                            Toast.makeText(this@MainActivity, "Incorrect", Toast.LENGTH_SHORT).show()
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
        val randomIndex =
            Random.nextInt(0, suggest.length + 1) // lấy ra random 1 vị trí trong suggest
        val first = suggest.substring(
            0,
            randomIndex
        ) // tách suggest thành 2 chuỗi r chèn ký tự từ a -> z vào
        val second = suggest.substring(randomIndex)
        return first + charter + second
    }

    private fun visibleNext() {
        binding.imgBgNext.visibility = View.VISIBLE
        binding.txtNext.visibility = View.VISIBLE
    }

    private fun inVisibleNext() {
        binding.imgBgNext.visibility = View.INVISIBLE
        binding.txtNext.visibility = View.INVISIBLE
    }

}