package com.example.calculator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var digit_on_screen = StringBuilder(12)
    var operation: Char = ' '
    var leftHandSide: Double = 0.0
    var rightHandSide: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result_id.text = "0"

        initializeButtons()
    }

    private fun initializeButtons() {
        functionalButtons()
        operationalButtons()
        numericalButtons()
    }





    private fun numericalButtons() {

        one_btn.setOnClickListener {
            appendToDigitOnScreen("1")
        }

        two_btn.setOnClickListener {
            appendToDigitOnScreen("2")
        }

        three_btn.setOnClickListener {
            appendToDigitOnScreen("3")
        }

        four_btn.setOnClickListener {
            appendToDigitOnScreen("4")
        }

        five_btn.setOnClickListener {
            appendToDigitOnScreen("5")
        }

        six_btn.setOnClickListener {
            appendToDigitOnScreen("6")
        }

        seven_btn.setOnClickListener {
            appendToDigitOnScreen("7")
        }

        eight_btn.setOnClickListener {
            appendToDigitOnScreen("8")
        }

        nine_btn.setOnClickListener {
            appendToDigitOnScreen("9")
        }

        zero_btn.setOnClickListener {
            appendToDigitOnScreen("0")
        }

        dot_btn.setOnClickListener {
            if (digit_on_screen.isEmpty()) {
                appendToDigitOnScreen("0.")
            }
            else {
                if (digit_on_screen.indexOf(".")==-1) {
                    appendToDigitOnScreen(".")
                }
            }

        }

        double_zero.setOnClickListener {
            if (digit_on_screen.isEmpty()) {
                return@setOnClickListener
            }
            else {
                appendToDigitOnScreen("00")
            }
        }


    }




    private fun appendToDigitOnScreen(digit: String) {


        digit_on_screen.append(digit)

        result_id.text = digit_on_screen.toString()
    }






    private fun operationalButtons() {

        addition_btn.setOnClickListener {
            selectOperation('A')
        }

        subtract_btn.setOnClickListener {
            selectOperation('S')
        }

        divide_btn.setOnClickListener {
            selectOperation('D')
        }

        multipy_btn.setOnClickListener {
            selectOperation('M')
        }

    }







    private fun selectOperation(c: Char) {
        if (digit_on_screen.isEmpty()) {
            return
        }
        operation = c

        leftHandSide = digit_on_screen.toString().toDouble()
        digit_on_screen.clear()
        result_id.text = "0"
    }







    private fun functionalButtons() {

        clear_everything_btn.setOnClickListener {
            digit_on_screen.clear()
            result_id.text = "0"
        }

        clear_btn.setOnClickListener {

            if (digit_on_screen.length <= 0) {
                return@setOnClickListener
            } else {
                clearDigit()
            }
        }

        backspace_btn.setOnClickListener {
            if (digit_on_screen.length <= 0) {
                return@setOnClickListener
            } else {
                clearDigit()
            }
        }

        equal_btn.setOnClickListener {
            if (leftHandSide.isNaN() or rightHandSide.isNaN() or operation.equals(' ')){
                return@setOnClickListener
            }
            else {
                performMathOperation()
            }
        }

    }





    private fun performMathOperation() {

        rightHandSide = digit_on_screen.toString().toDouble()


        when (operation) {

            'A' -> {
                val sum = OperationsHelper.add(leftHandSide, rightHandSide)
                result_id.text = sum.toString()
                digit_on_screen.clear()
                digit_on_screen.append(sum)
            }
            'S' -> {
                val subtract = OperationsHelper.subtract(leftHandSide, rightHandSide)
                result_id.text = subtract.toString()
                digit_on_screen.clear()
                digit_on_screen.append(subtract)
            }
            'M' -> {
                val multiply = OperationsHelper.multiply(leftHandSide, rightHandSide)
                result_id.text = multiply.toString()
                digit_on_screen.clear()
                digit_on_screen.append(multiply)
            }
            'D' -> {
                val divide = OperationsHelper.divide(leftHandSide, rightHandSide)
                result_id.text = divide.toString()
                digit_on_screen.clear()
                digit_on_screen.append(divide)
            }

        }

    }





    private fun clearDigit() {
        val length = digit_on_screen.length
        digit_on_screen.deleteCharAt(length - 1)
        if (length <= 0) {
            result_id.text = "0"
        }else{
            result_id.text = digit_on_screen.toString()
        }
    }
}
