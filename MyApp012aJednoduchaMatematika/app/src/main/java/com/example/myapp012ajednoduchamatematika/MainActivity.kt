//package com.example.myapp012ajednoduchamatematika
//
//import android.os.Bundle
//import android.os.CountDownTimer
//import android.view.LayoutInflater
//import android.view.View
//import android.widget.Button
//import android.widget.TextView
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import kotlinx.coroutines.NonCancellable.start
//import kotlin.random.Random
//
//class MainActivity : AppCompatActivity() {
//
//    var TimeTextView: TextView? = null
//    var QuestionTextText: TextView? = null
//    var ScoreTextView: TextView? = null
//    var AlertTextView: TextView? = null
//    var FinalScoreTextView: TextView? = null
//    var btn0: Button? = null
//    var btn1: Button? = null
//    var btn2: Button? = null
//    var btn3: Button? = null
//    var countDownTimer: CountDownTimer? = null
//    var random: Random = Random
//    var a = 0
//    var b = 0
//    var indexOfCorrectAnswer = 0
//    var answers = ArrayList<Int>()
//    var points = 0
//    var totalQuestions = 0
//    var cals = ""
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContentView(R.layout.activity_main)
//
//        val calInt = intent.getStringExtra("cals")
//        cals = calInt!!
//        TimeTextView = findViewById(R.id.TimeTextView)
//        QuestionTextText = findViewById(R.id.QuestionTextText)
//        ScoreTextView = findViewById(R.id.ScoreTextView)
//        AlertTextView = findViewById(R.id.AlertTextView)
//        //FinalScoreTextView = findViewById(R.id.FinalScoreTextView)
//        btn0 = findViewById(R.id.button0)
//        btn1 = findViewById(R.id.button1)
//        btn2 = findViewById(R.id.button2)
//        btn3 = findViewById(R.id.button3)
//
//        start()
//
//    }
//
//    fun NextQuestion(cal: String) {
//        a = random.nextInt(10)
//        b = random.nextInt(10)
//        QuestionTextText!!.text = "$a $cal $b"
//        indexOfCorrectAnswer = random.nextInt(4)
//        answers.clear()
//
//        for (i in 0..3) {
//            if (indexOfCorrectAnswer == i) {
//
//                when (cal) {
//                    "+" -> {
//                        answers.add(a + b)
//                    }
//                    "-" -> {
//                        answers.add(a - b)
//                    }
//                    "*" -> {
//                        answers.add(a * b)
//                    }
//                    "/" -> {
//                        try {
//                            answers.add(a / b)
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                        }
//                    }
//                }
//            } else {
//                var wrongAnswer = random.nextInt(20)
//                try {
//                    while (
//                        wrongAnswer == a + b
//                        || wrongAnswer == a - b
//                        || wrongAnswer == a * b
//                        || wrongAnswer == a / b
//                    ) {
//                        wrongAnswer = random.nextInt(20)
//                    }
//                    answers.add(wrongAnswer)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }
//        try {
//            btn0!!.text = "${answers[0]}"
//            btn1!!.text = "${answers[1]}"
//            btn2!!.text = "${answers[2]}"
//            btn3!!.text = "${answers[3]}"
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    fun optionSelect(view: View?) {
//        totalQuestions++
//        if (indexOfCorrectAnswer.toString() == view!!.tag.toString()) {
//            points++
//            AlertTextView!!.text = "Correct"
//        } else {
//            AlertTextView!!.text = "Wrong"
//        }
//        ScoreTextView!!.text = "$points/$totalQuestions"
//        NextQuestion(cals)
//
//    }
//
//    fun PlayAgain(view: View?) {
//        points = 0
//        totalQuestions = 0
//        ScoreTextView!!.text = "$points/$totalQuestions"
//        countDownTimer!!.start()
//    }
//
//    private fun start() {
//        NextQuestion(cals)
//        countDownTimer = object : CountDownTimer(10000, 500) {
//            override fun onTick(p0: Long) {
//                TimeTextView!!.text = (p0 / 1000).toString() + "s"
//            }
//
//            override fun onFinish() {
//                TimeTextView!!.text = "Konec času"
//                openDilog()
//            }
//        }.start()
//    }
//
//    private fun openDilog() {
//        val inflate = LayoutInflater.from(this)
//        var winDialog = inflate.inflate(R.layout.win_layout, null)
//        FinalScoreTextView = winDialog.findViewById(R.id.FinalScoreTextView)
//        val btnPlayAgain = winDialog.findViewById<Button>(R.id.buttonPlayAgain)
//        val btnBack = winDialog.findViewById<Button>(R.id.buttonBack)
//        var dialog = AlertDialog.Builder(this)
//        dialog.setCancelable(false)
//        dialog.setView(winDialog)
//        FinalScoreTextView!!.text = "$points/$totalQuestions"
//        btnPlayAgain.setOnClickListener {
//            PlayAgain(it)
//        }
//        btnBack.setOnClickListener {
//            onBackPressed()
//        }
//        val showDialog = dialog.create()
//        showDialog.show()
//    }
//
//}

package com.example.myapp012ajednoduchamatematika

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var timeTextView: TextView? = null
    private var questionTextView: TextView? = null
    private var scoreTextView: TextView? = null
    private var alertTextView: TextView? = null
    private var finalScoreTextView: TextView? = null
    private var btn0: Button? = null
    private var btn1: Button? = null
    private var btn2: Button? = null
    private var btn3: Button? = null
    private var countDownTimer: CountDownTimer? = null
    private var remainingTime = 10000L // Start with 10 seconds
    private val random = Random
    private var a = 0
    private var b = 0
    private var indexOfCorrectAnswer = 0
    private val answers = ArrayList<Int>()
    private var points = 0
    private var totalQuestions = 0
    private var cals = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cals = intent.getStringExtra("cals")!!
        timeTextView = findViewById(R.id.TimeTextView)
        questionTextView = findViewById(R.id.QuestionTextText)
        scoreTextView = findViewById(R.id.ScoreTextView)
        alertTextView = findViewById(R.id.AlertTextView)
        btn0 = findViewById(R.id.button0)
        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)

        startGame()
    }

    private fun startGame() {
        points = 0
        totalQuestions = 0
        remainingTime = 10000L
        scoreTextView!!.text = "$points/$totalQuestions"
        startTimer()
        nextQuestion(cals)
    }

    private fun nextQuestion(cal: String) {
        a = random.nextInt(10)
        b = random.nextInt(10)
        questionTextView!!.text = "$a $cal $b"
        indexOfCorrectAnswer = random.nextInt(4)
        answers.clear()

        for (i in 0..3) {
            if (indexOfCorrectAnswer == i) {
                answers.add(
                    when (cal) {
                        "+" -> a + b
                        "-" -> a - b
                        "*" -> a * b
                        "/" -> if (b != 0) a / b else a
                        else -> 0
                    }
                )
            } else {
                var wrongAnswer = random.nextInt(20)
                while (
                    wrongAnswer == a + b || wrongAnswer == a - b ||
                    wrongAnswer == a * b || (b != 0 && wrongAnswer == a / b)
                ) {
                    wrongAnswer = random.nextInt(20)
                }
                answers.add(wrongAnswer)
            }
        }

        btn0!!.text = answers[0].toString()
        btn1!!.text = answers[1].toString()
        btn2!!.text = answers[2].toString()
        btn3!!.text = answers[3].toString()
    }

    fun optionSelect(view: View?) {
        totalQuestions++
        if (indexOfCorrectAnswer.toString() == view!!.tag.toString()) {
            points++
            alertTextView!!.text = "Correct"
            remainingTime += 2500L // Add 2,5 seconds
            restartTimer()
        } else {
            alertTextView!!.text = "Wrong"
        }
        scoreTextView!!.text = "$points/$totalQuestions"
        nextQuestion(cals)
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(remainingTime, 500) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTime = millisUntilFinished
                timeTextView!!.text = "${remainingTime / 1000}s"
            }

            override fun onFinish() {
                timeTextView!!.text = "Time's up!"
                openDialog()
            }
        }.start()
    }

    private fun restartTimer() {
        countDownTimer?.cancel()
        startTimer()
    }

    private fun openDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.win_layout, null)
        finalScoreTextView = dialogView.findViewById(R.id.FinalScoreTextView)
        val btnPlayAgain = dialogView.findViewById<Button>(R.id.buttonPlayAgain)
        val btnBack = dialogView.findViewById<Button>(R.id.buttonBack)
        val dialog = AlertDialog.Builder(this).apply {
            setCancelable(false)
            setView(dialogView)
        }.create()

        finalScoreTextView!!.text = "$points/$totalQuestions"
        btnPlayAgain.setOnClickListener {
            dialog.dismiss()
            startGame()
        }
        btnBack.setOnClickListener {
            dialog.dismiss()
            onBackPressed()
        }
        dialog.show()
    }
}
