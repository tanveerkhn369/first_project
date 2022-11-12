package com.example.project

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.example.project.adapter.CustomAdapter
import com.example.project.databinding.ActivityMainBinding
import com.example.project.fragment.BottomSheetFragment

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var fragment: BottomSheetFragment
        lateinit var bottomSheetDialog: BottomSheetDialog
        lateinit var dialogFrameLayout: FrameLayout
        var firstPoint = "BIN"
        var secondPoint = "OCT"
        lateinit var focusTextView: TextView
        lateinit var binding: ActivityMainBinding
        var textMapping = mutableMapOf<String, String>()


    }

    private val list = arrayOf(
        "BinaryToDecimal",
        "BinaryToDecimal",
        "BinaryToDecimal",
        "BinaryToDecimal",
        "BinaryToDecimal",
        "BinaryToDecimal",
        "BinaryToDecimal"
    )

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //R.layout.activity_main
        setContentView(binding.root)
        initListener()
        textMapping["textView1"] = "BIN"
        textMapping["textView2"] = "OCT"
        with(binding) {
            focusTextView = textView1
            keyboardChange(textMapping["textView1"].toString())

            val a = ArrayList<String>()
            a.addAll(list)
            recyclerView.apply {
                adapter = CustomAdapter(this@MainActivity, a)
                layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            }


            delete.setOnClickListener {
                if (!focusTextView.text.equals("0") && focusTextView.text.toString().length != 1) {
                    focusTextView.apply {
                        val s = text.toString()
                        text = s.dropLast(1)
                    }
                } else
                    focusTextView.apply {
                        text = "0"
                    }
            }

            textAC.setOnClickListener {
                focusTextView.text = "0"
            }


            spinner1.setOnClickListener {
                val fragmentManager = supportFragmentManager
                fragment = BottomSheetFragment()
                fragment.arguments = Bundle().apply {
                    putString("point", firstPoint)
                    putInt("from", 1)

                }
                fragment.show(fragmentManager, fragment.tag)
//                bottomSheetDialog.show()

/*                fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, BottomSheetFragment(), null)
                    .commit()*/
            }

            spinner2.setOnClickListener {
                val fragmentManager = supportFragmentManager
                fragment = BottomSheetFragment()
                fragment.arguments = Bundle().apply {
                    putString("point", secondPoint)
                    putInt("from", 2)

                }
                fragment.show(fragmentManager, fragment.tag)
            }

            textView1.setOnClickListener {
                switchColor(textView1, textView2)
                keyboardChange(textMapping["textView1"].toString())

            }
            textView2.setOnClickListener {
                switchColor(textView2, textView1)
                keyboardChange(textMapping["textView2"].toString())
            }
            focusTextView.addTextChangedListener {
                val modeFrom: String = textMapping[focusTextView.tag]!!
                when (focusTextView.tag) {
                    "textView1" -> {
                        val modeTo = textMapping["textView2"]
                        modifyText(textView2, modeFrom, modeTo, textView1.text.toString())
                    }
                    "textView2" -> {
                        val modeTo = textMapping["textView1"]
                        modifyText(textView1,modeFrom, modeTo, textView2.text.toString())
                    }
                }

            }
        }
    }

    private fun modifyText(textView:TextView,modeFrom: String, modeTo: String?, string: String) {
        val result = when (modeFrom) {
            "BIN" -> when (modeTo) {
                "OCT" -> {
                    textView.text=Util().binaryToOcta(string)
                }
                "DEC" -> {
                    textView.text= Util().binaryToDecimal(string).toInt().toString()
                }
                //"HEX"
                else -> {
                    textView.text=Util().binaryToHexa(string)
                }
            }
            "OCT" -> when (modeTo) {
                "BIN" -> {
                    textView.text=Util().octalToBinary(string)
                }
                else -> {}
            }
            "DEC" -> when (modeTo) {
                "BIN" -> {
                    textView.text=Util().decimalToBinary(string.toInt())

                }
                else -> {}
            }
            //"HEX"
            else -> when (modeTo) {
                "BIN" -> {
                    textView.text=Util().hexaToBinary(string)
                }
                else -> {}
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun switchColor(textView1: TextView, textView2: TextView) {
        textView1.setTextColor(resources.getColor(R.color.project, theme))
        textView2.setTextColor(resources.getColor(R.color.black, theme))
        focusTextView = textView1
    }

    private fun initListener() {

    }

    fun textOnClick(view: View) {
        val t = view as TextView
        if (focusTextView.text.toString() != "0")
            focusTextView.text = focusTextView.text.toString() + t.text.toString()
        else
            focusTextView.text = t.text.toString()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun keyboardChange(mode: String) {
        with(binding) {
            when (mode) {
                "BIN" -> {
                    updateColor(arrayOf(0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))
                    /* setColor(text0,0)
                     setColor(text1,0)
                     setColor(text2,1)
                     setColor(text3,1)
                     setColor(text4,1)
                     setColor(text5,1)
                     setColor(text6,1)
                     setColor(text7,1)
                     setColor(text8,1)
                     setColor(text9,1)
                     setColor(textA,1)
                     setColor(textB,1)
                     setColor(textC,1)
                     setColor(textD,1)
                     setColor(textE,1)
                     setColor(textF,1)*/

                }
                "OCT" -> {
                    updateColor(arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1))

                }
                "DEC" -> {
                    updateColor(arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1))

                }
                //"HEX"
                else -> {
                    updateColor(arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))

                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun updateColor(a: Array<Int>) {
        with(binding) {
            setColor(text0, a[0])
            setColor(text1, a[1])
            setColor(text2, a[2])
            setColor(text3, a[3])
            setColor(text4, a[4])
            setColor(text5, a[5])
            setColor(text6, a[6])
            setColor(text7, a[7])
            setColor(text8, a[8])
            setColor(text9, a[9])
            setColor(textA, a[10])
            setColor(textB, a[11])
            setColor(textC, a[12])
            setColor(textD, a[13])
            setColor(textE, a[14])
            setColor(textF, a[15])
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun setColor(textView: TextView, color: Int) {
        textView.isEnabled = color == 0
        textView.setTextColor(
            resources.getColor(
                if (color == 0) R.color.black else R.color.light,
                theme
            )
        )
    }

}