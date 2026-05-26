package com.example.fnch2026

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

// Один Activity показывает создание и редактирование вакансии по шагам.
class CreateVacancyActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvStepValue: TextView
    private lateinit var stepOne: View
    private lateinit var stepTwo: View
    private lateinit var stepThree: View
    private lateinit var btnBack: MaterialButton
    private lateinit var btnPrimary: MaterialButton
    private var currentStep = 1
    private var mode = "create"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_vacancy)

        mode = intent.getStringExtra("mode") ?: "create"

        tvTitle = findViewById(R.id.tvCreateVacancyTitle)
        tvStepValue = findViewById(R.id.tvStepValue)
        stepOne = findViewById(R.id.layoutStepOne)
        stepTwo = findViewById(R.id.layoutStepTwo)
        stepThree = findViewById(R.id.layoutStepThree)
        btnBack = findViewById(R.id.btnStepBack)
        btnPrimary = findViewById(R.id.btnStepPrimary)

        findViewById<ImageButton>(R.id.btnCloseCreateVacancy).setOnClickListener {
            finish()
        }

        btnBack.setOnClickListener {
            if (currentStep == 1) {
                finish()
            } else {
                currentStep--
                updateStep()
            }
        }

        btnPrimary.setOnClickListener {
            if (currentStep < 3) {
                currentStep++
                updateStep()
            } else {
                Toast.makeText(this, if (mode == "edit") "Changes saved" else "Vacancy created", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        updateStep()
    }

    private fun updateStep() {
        val isEdit = mode == "edit"
        tvTitle.text = if (isEdit) "Edit Vacancy" else "Create Vacancy"
        tvStepValue.text = "Step $currentStep of 3"

        stepOne.visibility = if (currentStep == 1) View.VISIBLE else View.GONE
        stepTwo.visibility = if (currentStep == 2) View.VISIBLE else View.GONE
        stepThree.visibility = if (currentStep == 3) View.VISIBLE else View.GONE

        btnBack.text = if (currentStep == 1) "Close" else "Back"
        btnPrimary.text = when {
            currentStep < 3 -> "Continue"
            isEdit -> "Save Changes"
            else -> "Publish Vacancy"
        }
    }
}
