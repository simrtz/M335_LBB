package com.example.triviaquiz.api

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.example.triviaquiz.util.TriviaParameterStringBuilder
import org.json.JSONArray


class TriviaClient {

    private val baseUrl = "https://opentdb.com/api.php?"
    fun getQuestionSet(numberOfQuestions: Int,
                     category: Int?,
                     difficulty: String?,
                     questionType: String?,
                     requestQueue: RequestQueue,
                     onSuccess: (JSONArray) -> Unit, )
    {

        val requestUrlString = baseUrl + TriviaParameterStringBuilder
            .getTriviaParameterString(
                numberOfQuestions,
                category,
                difficulty,
                questionType
        )

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,
            requestUrlString,
            null,
            { response ->
                try {
                    onSuccess.invoke(response.getJSONArray("results"))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            { error ->
                error.printStackTrace()
            }
        )

        requestQueue.add(jsonObjectRequest)
    }

}