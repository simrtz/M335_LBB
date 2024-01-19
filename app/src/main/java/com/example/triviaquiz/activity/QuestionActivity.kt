package com.example.triviaquiz.activity

import android.content.Intent
import android.graphics.Color
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.triviaquiz.R
import com.example.triviaquiz.domain.Question
import com.example.triviaquiz.service.QuestionService
import com.squareup.seismic.ShakeDetector

const val NUMBER_OF_QUESTIONS_KEY = "NUMBER_OF_QUESTIONS"
const val CATEGORY_KEY = "CATEGORY"
const val DIFFICULTY_KEY = "DIFFICULTY"
const val TYPE_KEY = "TYPE"

class QuestionActivity : AppCompatActivity(), ShakeDetector.Listener {
    private val questionService = QuestionService()
    private var questionSet = listOf<Question>()
    private var currentQuestionIndex = 0
    private var selectedAnswer: Pair<Button, Int>? = null
    private var addedButtons: MutableList<Button> = mutableListOf()
    private var correctlyAnswered = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_main)

        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val shakeDetector = ShakeDetector(this)
        shakeDetector.start(sensorManager, SensorManager.SENSOR_DELAY_NORMAL)

        populateQuestionSet()
        populateView()
    }

    private fun populateView() {
        val questionText = findViewById<TextView>(R.id.question_text)

        questionText.text = questionSet[currentQuestionIndex].question

        val buttonSection = findViewById<View>(R.id.answer_button_section) as LinearLayout
        buttonSection.removeAllViews()

        val shuffledAnswerPool = getShuffledAnswerPool()
        addAnswerButtons(shuffledAnswerPool)
        setUpRevealButton(shuffledAnswerPool)
    }

    private fun populateQuestionSet() {
        val numberOfQuestions = intent.getIntExtra(NUMBER_OF_QUESTIONS_KEY, 0)
        val category = intent.getStringExtra(CATEGORY_KEY)!!
        val difficulty = intent.getStringExtra(DIFFICULTY_KEY)!!
        val type = intent.getStringExtra(TYPE_KEY)!!

        questionSet = questionService.getQuestionSet(numberOfQuestions, category, difficulty, type)
    }

    private fun getShuffledAnswerPool(): MutableList<String> {
        val answerPool = mutableListOf<String>()
        val currentQuestion = questionSet[currentQuestionIndex]

        answerPool.add(currentQuestion.correctAnswer)
        currentQuestion.incorrectAnswers.forEach { answer ->
            answerPool.add(answer)
        }
        answerPool.shuffle()

        return answerPool
    }

    private fun addAnswerButtons(answers: MutableList<String>) {
        val buttonSection = findViewById<LinearLayout>(R.id.answer_button_section)
        var color: Int
        addedButtons.clear()

        answers.forEachIndexed { index, answer ->
            val button = Button(this)
            button.text = answer

            setMargins(button)
            color = getBackgroundColor(index)
            button.setBackgroundColor(color)

            addedButtons.add(button)
            button.setOnClickListener {
                if (selectedAnswer != null) {
                    val originalColor = getBackgroundColor(selectedAnswer!!.second)
                    selectedAnswer!!.first.setBackgroundColor(originalColor)
                }

                button.setBackgroundResource(R.drawable.answer_box_selected_background)
                selectedAnswer = Pair(button, index)
            }

            buttonSection.addView(button)
        }
    }

    private fun setMargins(button: Button) {
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(10, 10, 10, 10)
        button.layoutParams = params
    }

    private fun getBackgroundColor(index: Int): Int {
        return when (index) {
            0 -> Color.parseColor("#9664FF")
            1 -> Color.parseColor("#627BFF")
            2 -> Color.parseColor("#D96969")
            3 -> Color.parseColor("#E68743")
            else -> 0
        }
    }

    private fun setUpRevealButton(answers: MutableList<String>) {
        findViewById<Button>(R.id.reveal_button).setOnClickListener { _ ->
            val correctAnswer = questionSet[currentQuestionIndex].correctAnswer

            answers.forEachIndexed { index, answer ->
                if (answer == correctAnswer) {
                    val button = addedButtons[index]
                    button.setBackgroundResource(R.drawable.answer_box_correct_background)
                }
            }

            if (selectedAnswer?.first?.text == correctAnswer) {
                correctlyAnswered++
            }

        }
    }

    override fun hearShake() {
        if (currentQuestionIndex < questionSet.size - 1) {
            currentQuestionIndex++
            populateView()
        } else {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(NUMBER_OF_QUESTIONS_KEY, questionSet.size)
            intent.putExtra(CORRECTLY_ANSWERED, correctlyAnswered)
            currentQuestionIndex = 0
            correctlyAnswered = 0
            startActivity(intent)
        }
    }

}