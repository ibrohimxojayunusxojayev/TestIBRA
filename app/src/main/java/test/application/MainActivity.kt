package test.application


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import java.util.*
import kotlin.collections.ArrayList


class MainActivity() : AppCompatActivity(), Parcelable {
    lateinit var questionsList: ArrayList<Test>
    private var index: Int = 0
    lateinit var questionModel: Test

    private var correctAnswerCount: Int = 0
    private var wrongAnswerCount: Int = 0
    lateinit var questions: TextView
    lateinit var var1: RadioButton
    lateinit var var2: RadioButton
    lateinit var var3: RadioButton
    lateinit var var4: RadioButton

    lateinit var nextbtn: AppCompatButton
    lateinit var backbtn: AppCompatButton
    lateinit var radioGroup: RadioGroup

    constructor(parcel: Parcel) : this() {
        index = parcel.readInt()
        correctAnswerCount = parcel.readInt()
        wrongAnswerCount = parcel.readInt()
    }


    //    var tests:MutableList<Test> = mutableListOf()
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        questions = findViewById(R.id.question)
        var1 = findViewById(R.id.radioButton1)
        var2 = findViewById(R.id.radioButton2)
        var3 = findViewById(R.id.radioButton3)
        var4 = findViewById(R.id.radioButton4)
        radioGroup = findViewById(R.id.radioGroup)
        nextbtn = findViewById(R.id.nextbtn)
        backbtn = findViewById(R.id.backbtn)



        questionsList = ArrayList()
        questionsList.add(
            Test(
                "What color is the sky?",
                "blue",
                "red",
                "balck",
                "green",
                "blue"
            )
        )
        questionsList.add(
            Test(
                "What is the speed of sound?",
                "120 km/h",
                "1,200 km/h",
                "400 km/h",
                "700 km/h",
                "1,200 km/h"
            )
        )

        questionsList.add(
            Test(
                "Which of the following animals can run the fastest?",
                "Cheetah",
                "Leopard",
                "Tiger",
                "Lion",
                "Cheetah"
            )
        )
        questionsList.add(
            Test(
                "Creator of Apple?",
                "Elon Musk",
                "Mark Zukerberk",
                "Stive Jobs",
                "Jim Kerry",
                "Stive Jobs"
            )
        )
        questionsList.add(
            Test(
                "75^2 + 7 = ?",
                "5631",
                "5632",
                "7898",
                "7446",
                "5632"
            )
        )

        questionModel = questionsList[index]


        nextbtn.setOnClickListener {
            index++
            this.createTest(index)

            if (index == questionsList.size) {
                showDialog()
            }

            var1.isChecked = false
            var2.isChecked = false
            var3.isChecked = false
            var4.isChecked = false
        }
        backbtn.setOnClickListener {
            index--
            this.createTest(index)

            var1.isChecked = false
            var2.isChecked = false
            var3.isChecked = false
            var4.isChecked = false

        }



        setAllQuestions()

    }

    private fun createTest(index: Int) {
        if (index == 0) {
            backbtn.isClickable = false
        }
        if (index < questionsList.size) {
            val test = questionsList[index]
            questions.text = test.question
            var1.text = test.var1
            var2.text = test.var2
            var3.text = test.var3
            var4.text = test.var4
        }

    }


    private fun correctAns() {
        correctAnswerCount++
    }

    private fun wrongAns() {
        wrongAnswerCount++
    }

    @SuppressLint("SuspiciousIndentation")
    fun option1Clicked(view: View) {

        if (questionModel.var1 == questionModel.answer) {
            correctAns()

        } else {
            wrongAns()
        }
    }

    fun option2Clicked(view: View) {

        if (questionModel.var2 == questionModel.answer) {
            correctAns()

        } else {
            wrongAns()
        }
    }

    fun option3Clicked(view: View) {

        if (questionModel.var3 == questionModel.answer) {
            correctAns()


        } else {
            wrongAns()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun option4Clicked(view: View) {

        if (questionModel.var4 == questionModel.answer) {
            correctAns()

        } else {
            wrongAns()
        }
    }

    private fun setAllQuestions() {
        var1.text = questionModel.var1
        var2.text = questionModel.var2
        var3.text = questionModel.var3
        var4.text = questionModel.var4
        questions.text = questionModel.question

        if (index == 0) {
            backbtn.isClickable = false
        }

    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(index)
        parcel.writeInt(correctAnswerCount)
        parcel.writeInt(wrongAnswerCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.activity_dialog)

        val body = dialog.findViewById(R.id.motive) as TextView
        body.text = correctAnswerCount.toString() + "/" + questionsList.size
        val yesBtn = dialog.findViewById(R.id.menu) as AppCompatButton
        val noBtn = dialog.findViewById(R.id.restart) as AppCompatButton
        val goHome = dialog.findViewById(R.id.home) as AppCompatButton
        yesBtn.setOnClickListener {
            dialog.dismiss()
        }
        noBtn.setOnClickListener {
            index = 0
            correctAnswerCount = 0
            setAllQuestions()
            dialog.dismiss()
        }
        goHome.setOnClickListener {
            finish()
            dialog.dismiss()
        }
        dialog.show()
    }

}


private fun TextView.setText(d: Double) {

}



