package com.example.triviaquiz.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.triviaquiz.R


const val CORRECTLY_ANSWERED = "CORRECTLY_ANSWERED"
class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val numberOfQuestions = intent.getIntExtra(NUMBER_OF_QUESTIONS_KEY, 0)
        val correctlyAnswered = intent.getIntExtra(CORRECTLY_ANSWERED, 0)

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