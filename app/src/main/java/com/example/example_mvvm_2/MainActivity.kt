package com.example.example_mvvm_2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.example_mvvm_2.Model.Question
import com.example.example_mvvm_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var lsPic = mutableListOf<Question>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        lsPic.add(Question(R.drawable.baocao,"baocao"))
        lsPic.add(Question(R.drawable.aomua,"aomua"))
        lsPic.add(Question(R.drawable.canthiep,"canthiep"))
        lsPic.add(Question(R.drawable.cattuong,"cattuong"))
        lsPic.add(Question(R.drawable.chieutre,"chieutre"))
        lsPic.add(Question(R.drawable.danong,"danong"))
        lsPic.add(Question(R.drawable.danhlua,"danhlua"))
        lsPic.add(Question(R.drawable.giandiep,"giandiep"))

        Log.e("TAG",lsPic[1].answer.length.toString())

    }


}