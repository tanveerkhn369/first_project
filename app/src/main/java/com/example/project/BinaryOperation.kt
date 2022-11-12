package com.example.project

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.project.databinding.ActivityBinaryOperationBinding

class BinaryOperation : AppCompatActivity() {
    lateinit var binding: ActivityBinaryOperationBinding
    var selectOperation = "add"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBinaryOperationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            calculate.setOnClickListener {

                if (isFieldValid()) {

                    val number1 = number1.text.toString()
                    val number2 = number2.text.toString()

                    when (selectOperation) {
                        "add" -> {
                            val c = Util().borrowZero(number1, number2)
                            result.text = Util().binaryAdd(c[0], c[1])
                        }
                        "sub" -> {
                            val c = Util().borrowZero(number1, number2)
                            result.text = Util().binarySub(c[0], c[1])
                        }
                        "mul" ->{
                            val c = Util().borrowZero(number1, number2)
                            result.text = Util().binaryMul(c[0], c[1])
                        }
                        "divide" -> {
                            val a=Util().binaryDiv(number1, number2)
                            result.text = "${a[0]}  Reminder:${a[1]}"
                        }
                    }
                } else
                    Toast.makeText(
                        this@BinaryOperation,
                        "Please Enter Only 0 and 1 Characters",
                        Toast.LENGTH_SHORT
                    ).show()
            }

        }
    }

    private fun isFieldValid(): Boolean {
        with(binding) {
            val s = number1.text.toString()
            val s1 = number2.text.toString()
            if (s.isEmpty()){
                Toast.makeText(
                    this@BinaryOperation,
                    "Please Enter Number1 Field",
                    Toast.LENGTH_SHORT
                ).show()
            return false}

        if (s1.isEmpty()){
                Toast.makeText(
                    this@BinaryOperation,
                    "Please Enter Number2 Field",
                    Toast.LENGTH_SHORT
                ).show()
            return false}
            repeat(s.length) {
                if (s[it].toString() != "0" && s[it].toString() != "1")
                    return false
            }
            repeat(s1.length) {
                if (s1[it].toString() != "0" && s1[it].toString() != "1")
                    return false
            }
            return true
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun cardOnClick(view: View) {
        val card = view as CardView
        selectOperation = card.tag.toString()
        with(binding) {
            when (card.tag) {
                "add" ->
                    changeCardColor(card, add, arrayOf(0, 1, 1, 1))
                "sub" -> changeCardColor(card, sub, arrayOf(1, 0, 1, 1))
                "mul" -> changeCardColor(card, mul, arrayOf(1, 1, 0, 1))
                "divide" -> changeCardColor(card, divide, arrayOf(1, 1, 1, 0))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun changeCardColor(cardView: CardView, textView: TextView, a: Array<Int>) {

        with(binding) {
            setColor(addCardView, add, a[0])
            setColor(subCardView, sub, a[1])
            setColor(mulCardView, mul, a[2])
            setColor(divideCardView, divide, a[3])


        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setColor(cardView: CardView, textView: TextView, color: Int) {
        cardView.setBackgroundColor(
            this.resources.getColor(
                if (color == 0) R.color.project else R.color.white,
                theme
            )
        )
        textView.setBackgroundColor(
            this.resources.getColor(
                if (color == 0) R.color.project else R.color.white,
                theme
            )
        )
        textView.setTextColor(
            this.resources.getColor(
                if (color == 0) R.color.white else R.color.black,
                theme
            )
        )

    }

}