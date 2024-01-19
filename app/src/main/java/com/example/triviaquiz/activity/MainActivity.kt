package com.example.triviaquiz.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.triviaquiz.R
import com.example.triviaquiz.api.TriviaClient
import com.example.triviaquiz.domain.Question
import com.example.triviaquiz.domain.questionDataTypes.Category
import com.example.triviaquiz.domain.questionDataTypes.Difficulty
import com.example.triviaquiz.domain.questionDataTypes.Type
import org.json.JSONArray
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    private val triviaClient = TriviaClient()
    private var questions = arrayListOf<Question>()
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
            populateQuestions()
        }
    }

    private fun populateQuestions() {
        val numberOfQuestions = findViewById<EditText>(R.id.number_of_question_text).text.toString().toInt()
        val categoryString = findViewById<Spinner>(R.id.category_dropdown).selectedItem.toString()
        val difficultyString = findViewById<Spinner>(R.id.difficulty_dropdown).selectedItem.toString()
        val typeString = findViewById<Spinner>(R.id.question_type_dropdown).selectedItem.toString()

        val category = Category.fromStringName(categoryString)
        val difficulty = Difficulty.fromStringName(difficultyString)
        val type = Type.fromStringName(typeString)

        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        triviaClient.getQuestionSet(numberOfQuestions, category?.apiValue, difficulty?.apiValue, type?.apiValue, requestQueue, this::onSuccess)
    }
    private fun onSuccess(jsonArray: JSONArray){
        questions = parseQuestionData(jsonArray)

        val bundle = Bundle()
        bundle.putSerializable(QUESTION_SET_KEY, questions as Serializable)

        val intent = Intent(this, QuestionActivity::class.java)
        intent.putExtra(BUNDLE_KEY, bundle)
        startActivity(intent)
    }

    private fun parseQuestionData(jsonArray: JSONArray): ArrayList<Question> {
        val questions = arrayListOf<Question>()

        for (index in 0 until jsonArray.length()) {
            val jsonQuestion = jsonArray.getJSONObject(index)

            val type = Type.fromApiValue(jsonQuestion.getString("type"))
            val difficulty = Difficulty.fromApiValue(jsonQuestion.getString("difficulty"))
            val category = Category.fromStringName(jsonQuestion.getString("category"))
            val question = jsonQuestion.getString("question")
            val correctAnswer = jsonQuestion.getString("correct_answer")
            val incorrectAnswersJsonArray = jsonQuestion.getJSONArray("incorrect_answers")

            val incorrectAnswersList = mutableListOf<String>()
            for (i in 0 until incorrectAnswersJsonArray.length()) {
                incorrectAnswersList.add(incorrectAnswersJsonArray.getString(i))
            }

            val questionObject = Question(type, difficulty, category, question, correctAnswer, incorrectAnswersList)
            questions.add(questionObject)
        }

        return questions
    }
}