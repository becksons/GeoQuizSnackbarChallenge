package com.example.snackbarchallenge

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import com.example.snackbarchallenge.R
import com.google.android.material.snackbar.Snackbar

data class Question(@StringRes val textResId: Int, val answer: Boolean)

class MainActivity : ComponentActivity() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_amazon, true))


    private var currentQIndex = 0

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.TrueButton)
        falseButton = findViewById(R.id.FalseButton)
        nextButton = findViewById(R.id.NextButton)
        questionView = findViewById(R.id.question)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentQIndex = (currentQIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentQIndex].textResId
        questionView.setText(questionTextResId)
    }

    private fun showSnackbar(s: String) {
        val view = findViewById<View>(android.R.id.content)
        Snackbar.make(view, s, Snackbar.LENGTH_SHORT).show()
    }

    private fun checkAnswer(selected: Boolean) {
        val correctAnswer = questionBank[currentQIndex].answer
        if (selected == correctAnswer) {
            showSnackbar("Correct")
        } else {
            showSnackbar("Incorrect")
        }
    }
}
