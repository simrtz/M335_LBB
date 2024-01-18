package com.example.triviaquiz.domain

import com.example.triviaquiz.domain.questionDataTypes.Category
import com.example.triviaquiz.domain.questionDataTypes.Difficulty
import com.example.triviaquiz.domain.questionDataTypes.Type

data class Question (
    var type: Type,
    var difficulty: Difficulty,
    var category: Category,
    var question: String,
    var correctAnswer: String,
    var incorrectAnswers: List<String>
)