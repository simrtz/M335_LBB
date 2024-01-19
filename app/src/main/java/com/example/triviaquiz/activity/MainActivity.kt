package com.example.triviaquiz.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.triviaquiz.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpForm()
        setUpButton()
    }

    private fun setUpForm() {
        val categorySelection = findViewById<Spinner>(R.id.category_dropdown)
        val difficultySelection = findViewById<Spinner>(R.id.difficulty_dropdown)
        val typeSelection = findViewById<Spinner>(R.id.question_type_dropdown)

        val categoryAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.categories,
            android.R.layout.simple_spinner_item
        )
        val difficultyAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.difficulties,
            android.R.layout.simple_spinner_item
        )
        val typeAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.question_types,
            android.R.layout.simple_spinner_item
        )

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        categorySelection.adapter = categoryAdapter
        difficultySelection.adapter = difficultyAdapter
        typeSelection.adapter = typeAdapter
    }

    private fun setUpButton() {
        val startButton = findViewById<Button>(R.id.start_button)

        startButton.setOnClickListener { _ ->
            val intent = Intent(this, QuestionActivity::class.java)
            val numberOfQuestions = findViewById<EditText>(R.id.number_of_question_text)
            val category = findViewById<Spinner>(R.id.category_dropdown)
            val difficulty = findViewById<Spinner>(R.id.difficulty_dropdown)
            val type = findViewById<Spinner>(R.id.question_type_dropdown)

            intent.putExtra(NUMBER_OF_QUESTIONS_KEY, numberOfQuestions.text.toString().toInt())
            intent.putExtra(CATEGORY_KEY, category.selectedItem.toString())
            intent.putExtra(DIFFICULTY_KEY, difficulty.selectedItem.toString())
            intent.putExtra(TYPE_KEY, type.selectedItem.toString())

            startActivity(intent)
        }
    }
}