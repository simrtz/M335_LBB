package com.example.triviaquiz.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.triviaquiz.R
import com.example.triviaquiz.api.TriviaClient


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val correctlyAnswered = 0;
        val numberOfQuestions = intent.getIntExtra("NUMBER_OF_QUESTIONS", 0)

        val resultStatisticView = findViewById<TextView>(R.id.result_text)

        val populatedResults = resources.getString(R.string.result_text, correctlyAnswered, numberOfQuestions)
        resultStatisticView.text = populatedResults

        val menuButton = findViewById<Button>(R.id.menu_button)
        menuButton.setOnClickListener { _ ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}