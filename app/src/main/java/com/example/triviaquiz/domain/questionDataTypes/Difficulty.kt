package com.example.triviaquiz.domain.questionDataTypes

enum class Difficulty(val apiValue: String, val stringName: String) {
    ANY_DIFFICULTY("", "Any Difficulty"),
    EASY("easy", "Easy"),
    MEDIUM("medium", "Medium"),
    HARD("hard", "Hard");
    companion object {
        fun fromStringName(stringName: String): Difficulty? {
            return entries.find { it.stringName == stringName }
        }

        fun fromApiValue(apiValue: String): Difficulty? {
            return entries.find { it.apiValue == apiValue }
        }
    }
}