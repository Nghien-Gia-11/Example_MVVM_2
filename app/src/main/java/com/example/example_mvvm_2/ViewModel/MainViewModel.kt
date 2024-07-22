package com.example.example_mvvm_2.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example_mvvm_2.Model.Question
import com.example.example_mvvm_2.R

class MainViewModel() : ViewModel() {

    private var _listPicture = MutableLiveData<List<Question>>()
    val listPicture : LiveData<List<Question>> get() = _listPicture

    init {
        _listPicture.value = addImagePath()
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

}