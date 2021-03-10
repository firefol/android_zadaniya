package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

   // private lateinit var binding: ActivityMainBinding

    //private val myName: MyName = MyName("Александр Седов")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(R.layout.activity_main)
       // binding.myName = myName
        findViewById<Button>(R.id.Buton_done).setOnClickListener {
           addNickname(it)
       }
      //  binding.ButonDone.setOnClickListener {
//addNickname(it)
      //  }

    }

    private fun addNickname(view:View){

       // binding.apply {
           // binding.nicknameText.text = binding.Nickname.text
          //  myName?.nickname = "Firefol"
           // invalidateAll()
           // binding.Nickname.visibility = View.GONE
           // binding.ButonDone.visibility = View.GONE
            //binding.nicknameText.visibility = View.VISIBLE

        val editText = findViewById<EditText>(R.id.Nickname)
        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)

        nicknameTextView.text = editText.text
        editText.visibility = View.GONE
        view.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
