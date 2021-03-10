package com.example.myfirstapp

import android.content.Intent
import kotlin.random.Random
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun toastMe(view: View) {
        // val myToast = Toast.makeText(this, message, duration);
        val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun countMe(view: View){
        val countString = textView.text.toString()
        var count: Int = Integer.parseInt(countString)
        count++
        textView.text = count.toString()
    }

    fun randomMe(view: View){

        val randomIntent= Intent(this,SecondActivity::class.java)
        val count = Random.nextInt(0, 100)
        textView.text = count.toString()
        randomIntent.putExtra(SecondActivity.TOTAL_COUNT, count)
       startActivity(randomIntent)

    }
}
