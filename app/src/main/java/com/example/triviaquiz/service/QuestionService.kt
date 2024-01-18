package com.example.triviaquiz.service

import com.example.triviaquiz.api.TriviaClient
import com.example.triviaquiz.domain.Question
import com.example.triviaquiz.domain.questionDataTypes.Category
import com.example.triviaquiz.domain.questionDataTypes.Difficulty
import com.example.triviaquiz.domain.questionDataTypes.Type

class QuestionService {

    private val triviaClient = TriviaClient()
    private var correctAnswers = 0;


    fun getQuestionSet(): List<Question> {

        return listOf(
            Question(
                Type.ANY_TYPE,
                Difficulty.ANY_DIFFICULTY,
                Category.ANY_CATEGORY,
                "",
                "",
                listOf("1", "2")
                ),
            Question(
                Type.ANY_TYPE,
                Difficulty.ANY_DIFFICULTY,
                Category.ANY_CATEGORY,
                "",
                "",
                listOf("1", "2")
            ),
            Question(
                Type.ANY_TYPE,
                Difficulty.ANY_DIFFICULTY,
                Category.ANY_CATEGORY,
                "",
                "",
                listOf("1", "2")
            ),
            Question(
                Type.ANY_TYPE,
                Difficulty.ANY_DIFFICULTY,
                Category.ANY_CATEGORY,
                "",
                "",
                listOf("1", "2")
            )
        )
    }

    fun validateAnswer(answer: String, question: Question) {
        if (question.correctAnswer == answer) {
            correctAnswers++
        }
    }
}