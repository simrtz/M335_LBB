package com.example.triviaquiz.util

class TriviaParameterStringBuilder {
    companion object {
        fun getTriviaParameterString(numberOfQuestions: Int,
                            category: Int?,
                            difficulty: String?,
                            questionType: String?): String
        {
            val parameters = StringBuilder()
            parameters.append("amount=$numberOfQuestions&")

            if (category != 0) {
                parameters.append("category=$category&")
            }
            if (difficulty != "") {
                parameters.append("difficulty=$difficulty&")
            }
            if (questionType != "") {
                parameters.append("type=$questionType&")
            }

            return parameters.toString()
        }
    }
}