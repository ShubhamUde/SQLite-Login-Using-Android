package com.shubham.sqlitelogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shubham.sqlitelogin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.btnLogin.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            loginDatabase(username, password)
        }

        binding.txtNewAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun loginDatabase(username: String, password: String) {

        val userExists = databaseHelper.readUser(username, password)
        if (userExists) {
            Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
        }
    }
}