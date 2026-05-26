package com.example.fnch2026

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

// Детали кандидата содержат смену статуса, сохранение и удаление карточки.
class CandidateDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate_details)

        findViewById<ImageButton>(R.id.btnBackCandidate).setOnClickListener {
            finish()
        }

        val spinnerStatus = findViewById<Spinner>(R.id.spinnerCandidateStatus)
        val statuses = listOf("Interviewing", "Technical Interview", "Test Task", "Offer Sent", "Hired", "Rejected")
        spinnerStatus.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, statuses)

        findViewById<MaterialButton>(R.id.btnSaveCandidate).setOnClickListener {
            Toast.makeText(this, "Candidate card updated", Toast.LENGTH_SHORT).show()
        }

        findViewById<MaterialButton>(R.id.btnDeleteCandidate).setOnClickListener {
            showDeleteDialog()
        }

        findViewById<MaterialButton>(R.id.btnSelectStatus).setOnClickListener {
            showStatusDialog(statuses)
        }
    }

    private fun showStatusDialog(statuses: List<String>) {
        AlertDialog.Builder(this)
            .setTitle("Select Status")
            .setSingleChoiceItems(statuses.toTypedArray(), 0, null)
            .setPositiveButton("Update Status") { _, _ ->
                Toast.makeText(this, "Статус кандидата обновлен", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showDeleteDialog() {
        val reasons = arrayOf("Hired for this role", "Not Selected / Rejected", "Candidate Withdrew", "Position Cancelled", "Other")

        AlertDialog.Builder(this)
            .setTitle("Remove Candidate Card")
            .setSingleChoiceItems(reasons, 0, null)
            .setPositiveButton("Remove from Board") { _, _ ->
                Toast.makeText(this, "Candidate removed from board", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
