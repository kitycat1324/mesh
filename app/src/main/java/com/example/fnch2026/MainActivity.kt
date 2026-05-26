package com.example.fnch2026

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var loginContainer: View
    private lateinit var registerContainer: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginContainer = findViewById(R.id.layoutLoginCard)
        registerContainer = findViewById(R.id.layoutRegisterCard)

        setupLoginScreen()
        setupRegistrationScreen()
        showLogin()
    }

    private fun setupLoginScreen() {
        val editLogin = findViewById<TextInputEditText>(R.id.editTextLoginEmail)
        val editPassword = findViewById<TextInputEditText>(R.id.editTextLoginPassword)
        val rememberMe = findViewById<CheckBox>(R.id.checkRememberMe)
        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val tvOpenRegister = findViewById<TextView>(R.id.tvOpenRegister)

        btnLogin.setOnClickListener {
            val email = editLogin.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Введите username или email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.contains("@") && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Введите корректный email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 8) {
                Toast.makeText(this, "Пароль должен быть не менее 8 символов", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (rememberMe.isChecked) {
                Toast.makeText(this, "Пользователь будет запомнен", Toast.LENGTH_SHORT).show()
            }

            startActivity(Intent(this, LoadingActivity::class.java))
        }

        tvForgotPassword.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Forgot Password")
                .setMessage("Сброс пароля отправлен на email")
                .setPositiveButton("OK", null)
                .show()
        }

        tvOpenRegister.setOnClickListener {
            showRegister()
        }
    }

    private fun setupRegistrationScreen() {
        val firstName = findViewById<TextInputEditText>(R.id.editTextFirstName)
        val lastName = findViewById<TextInputEditText>(R.id.editTextLastName)
        val email = findViewById<TextInputEditText>(R.id.editTextRegisterEmail)
        val password = findViewById<TextInputEditText>(R.id.editTextRegisterPassword)
        val confirmPassword = findViewById<TextInputEditText>(R.id.editTextConfirmPassword)
        val agreeTerms = findViewById<CheckBox>(R.id.checkTerms)
        val btnRegister = findViewById<MaterialButton>(R.id.btnRegister)
        val tvOpenLogin = findViewById<TextView>(R.id.tvOpenLogin)

        btnRegister.setOnClickListener {
            val first = firstName.text.toString().trim()
            val last = lastName.text.toString().trim()
            val emailValue = email.text.toString().trim()
            val passwordValue = password.text.toString().trim()
            val confirmValue = confirmPassword.text.toString().trim()

            if (first.isEmpty() || last.isEmpty()) {
                Toast.makeText(this, "Введите имя и фамилию", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
                Toast.makeText(this, "Введите корректный email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (passwordValue.length < 8) {
                Toast.makeText(this, "Минимум 8 символов", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (passwordValue != confirmValue) {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!agreeTerms.isChecked) {
                Toast.makeText(this, "Подтвердите согласие с условиями", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Регистрация завершена. Теперь войдите.", Toast.LENGTH_SHORT).show()
            showLogin()
        }

        tvOpenLogin.setOnClickListener {
            showLogin()
        }
    }

    private fun showLogin() {
        loginContainer.visibility = View.VISIBLE
        registerContainer.visibility = View.GONE
    }

    private fun showRegister() {
        loginContainer.visibility = View.GONE
        registerContainer.visibility = View.VISIBLE
    }
}
