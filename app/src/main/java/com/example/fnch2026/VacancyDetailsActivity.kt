package com.example.fnch2026

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

// Экран подробной информации по вакансии повторяет карточку вакансии из макета.
class VacancyDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vacancy_details)

        findViewById<ImageButton>(R.id.btnBackVacancy).setOnClickListener {
            finish()
        }

        findViewById<MaterialButton>(R.id.btnEditVacancy).setOnClickListener {
            val intent = Intent(this, CreateVacancyActivity::class.java)
            intent.putExtra("mode", "edit")
            startActivity(intent)
        }

        findViewById<MaterialButton>(R.id.btnDeleteVacancy).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Delete Vacancy")
                .setMessage("Вакансия будет удалена из списка.")
                .setPositiveButton("Delete") { _, _ ->
                    Toast.makeText(this, "Vacancy deleted", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}
