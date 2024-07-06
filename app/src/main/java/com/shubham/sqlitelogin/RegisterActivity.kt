package com.shubham.sqlitelogin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shubham.sqlitelogin.databinding.ActivityMainBinding
import com.shubham.sqlitelogin.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var databaseHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.btnRegister.setOnClickListener {
            val username = binding.reUsername.text.toString()
            val password = binding.rePassword.text.toString()
            loginDatabase(username, password)
        }

        binding.loginTextMy.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginDatabase(username: String, password: String) {

        val insertedRowId = databaseHelper.insertData(username, password)
        if (insertedRowId != -1L) {
            Toast.makeText(this, "Registration successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
        }

    }
}