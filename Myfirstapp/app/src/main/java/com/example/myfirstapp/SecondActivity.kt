package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {


    companion object {

        const val TOTAL_COUNT = "total_count"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        showRandomNumber()
    }

    fun showRandomNumber() {

        // Get the count from the intent extras

        val count = intent.getIntExtra(TOTAL_COUNT, 0)

        textView_rand.text = Integer.toString(count)

}
    }