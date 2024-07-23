package com.example.example_mvvm_2.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example_mvvm_2.Model.Question
import com.example.example_mvvm_2.R
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private var _listPicture = MutableLiveData<List<Question>>()
    val listPicture : LiveData<List<Question>> get() = _listPicture

    private var _score = MutableLiveData<Int>()
    val score : LiveData<Int> get() = _score

    private var _heart = MutableLiveData<Int>()
    val heart : LiveData<Int> get() = _heart


    init {
        _listPicture.value = addImagePath()
        _score.value = 0
        _heart.value = 5
    }

    private fun addImagePath() : List<Question>{
        val listPic = mutableListOf<Question>()
        listPic.add(Question(R.drawable.aomua, "aomua"))
        listPic.add(Question(R.drawable.baocao, "baocao"))
        listPic.add(Question(R.drawable.canthiep, "canthiep"))
        listPic.add(Question(R.drawable.cattuong, "cattuong"))
        listPic.add(Question(R.drawable.chieutre, "chieutre"))
        listPic.add(Question(R.drawable.danhlua, "danhlua"))
        listPic.add(Question(R.drawable.danong, "danong"))
        listPic.add(Question(R.drawable.giandiep, "giandiep"))
        return listPic
    }

    fun getScore(){
        viewModelScope.launch {
            _score.value = _score.value?.plus(100)
        }
    }

    fun getHeart(){
        viewModelScope.launch {
            _heart.value = _heart.value?.minus(1)
        }
    }


}