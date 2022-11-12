package com.example.project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            table.setOnClickListener {
                startActivity(Intent(this@HomeActivity, TruthTable::class.java))
            }
            system.setOnClickListener {
                startActivity(Intent(this@HomeActivity, MainActivity::class.java))
            }
            operation.setOnClickListener {
                startActivity(Intent(this@HomeActivity, BinaryOperation::class.java))
            }
        }

    }
}