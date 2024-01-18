package com.example.triviaquiz.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.triviaquiz.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val categorySelection = findViewById<Spinner>(R.id.category_dropdown)
        val difficultySelection = findViewById<Spinner>(R.id.difficulty_dropdown)
        val typeSelection = findViewById<Spinner>(R.id.question_type_dropdown)

        val categoryAdapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item)
        val difficultyAdapter = ArrayAdapter.createFromResource(this, R.array.difficulties, android.R.layout.simple_spinner_item)
        val typeAdapter = ArrayAdapter.createFromResource(this, R.array.question_types, android.R.layout.simple_spinner_item)

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        categorySelection.adapter = categoryAdapter
        difficultySelection.adapter = difficultyAdapter
        typeSelection.adapter = typeAdapter
    }
}