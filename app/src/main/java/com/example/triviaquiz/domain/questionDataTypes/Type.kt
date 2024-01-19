package com.example.triviaquiz.domain.questionDataTypes

enum class Type(val apiValue: String, val stringName: String) {
    ANY_TYPE("", "Any Type"),
    MULTIPLE_CHOICE("multiple", "Multiple Choice"),
    TRUE_FALSE("boolean", "True/False");
    companion object {
        fun fromStringName(stringName: String): Type? {
            return entries.find { it.stringName == stringName }
        }

        fun fromApiValue(apiValue: String): Type? {
            return entries.find { it.apiValue == apiValue }
        }
    }
}