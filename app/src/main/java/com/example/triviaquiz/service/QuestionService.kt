package com.example.triviaquiz.service

import com.example.triviaquiz.api.TriviaClient
import com.example.triviaquiz.domain.Question
import com.example.triviaquiz.domain.questionDataTypes.Category
import com.example.triviaquiz.domain.questionDataTypes.Difficulty
import com.example.triviaquiz.domain.questionDataTypes.Type

class QuestionService {

    private val triviaClient = TriviaClient()

    fun getQuestionSet(numberOfQuestions: Int, category: String, difficulty: String, type: String): List<Question> {

        return listOf(
            Question(
                Type.ANY_TYPE,
                Difficulty.ANY_DIFFICULTY,
                Category.ANY_CATEGORY,
                "test test test testtesttesttest test test",
                "3",
                listOf("1", "2", "4")
                ),
            Question(
                Type.ANY_TYPE,
                Difficulty.ANY_DIFFICULTY,
                Category.ANY_CATEGORY,
                "test 2",
                "1",
                listOf("2")
            ),
            Question(
                Type.ANY_TYPE,
                Difficulty.ANY_DIFFICULTY,
                Category.ANY_CATEGORY,
                "test test test testtesttesttest test test",
                "1",
                listOf("2")
            ),
            Question(
                Type.ANY_TYPE,
                Difficulty.ANY_DIFFICULTY,
                Category.ANY_CATEGORY,
                "test test test testtesttesttest test test",
                "4",
                listOf("1", "2", "3")
            )
        )
    }
}