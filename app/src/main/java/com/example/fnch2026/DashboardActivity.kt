package com.example.fnch2026

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText

// Главный экран хранит 4 раздела приложения и переключает их как простые вкладки.
class DashboardActivity : AppCompatActivity() {

    private lateinit var toolbarTitle: TextView
    private lateinit var sectionVacancies: View
    private lateinit var sectionCandidates: View
    private lateinit var sectionAi: View
    private lateinit var sectionProfile: View

    private lateinit var navVacancies: LinearLayout
    private lateinit var navCandidates: LinearLayout
    private lateinit var navAi: LinearLayout
    private lateinit var navProfile: LinearLayout

    private lateinit var navVacanciesText: TextView
    private lateinit var navCandidatesText: TextView
    private lateinit var navAiText: TextView
    private lateinit var navProfileText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        toolbarTitle = findViewById(R.id.tvDashboardTitle)
        sectionVacancies = findViewById(R.id.sectionVacancies)
        sectionCandidates = findViewById(R.id.sectionCandidates)
        sectionAi = findViewById(R.id.sectionAi)
        sectionProfile = findViewById(R.id.sectionProfile)

        navVacancies = findViewById(R.id.navVacancies)
        navCandidates = findViewById(R.id.navCandidates)
        navAi = findViewById(R.id.navAi)
        navProfile = findViewById(R.id.navProfile)

        navVacanciesText = findViewById(R.id.navVacanciesText)
        navCandidatesText = findViewById(R.id.navCandidatesText)
        navAiText = findViewById(R.id.navAiText)
        navProfileText = findViewById(R.id.navProfileText)

        setupNavigation()
        setupVacancySection()
        setupCandidatesSection()
        setupAiSection()
        setupProfileSection()

        showSection("vacancies")
    }

    private fun setupNavigation() {
        navVacancies.setOnClickListener { showSection("vacancies") }
        navCandidates.setOnClickListener { showSection("candidates") }
        navAi.setOnClickListener { showSection("ai") }
        navProfile.setOnClickListener { showSection("profile") }
    }

    private fun showSection(sectionName: String) {
        sectionVacancies.visibility = if (sectionName == "vacancies") View.VISIBLE else View.GONE
        sectionCandidates.visibility = if (sectionName == "candidates") View.VISIBLE else View.GONE
        sectionAi.visibility = if (sectionName == "ai") View.VISIBLE else View.GONE
        sectionProfile.visibility = if (sectionName == "profile") View.VISIBLE else View.GONE

        navVacanciesText.setTextColor(getColor(if (sectionName == "vacancies") R.color.blue_login else R.color.text_secondary))
        navCandidatesText.setTextColor(getColor(if (sectionName == "candidates") R.color.blue_login else R.color.text_secondary))
        navAiText.setTextColor(getColor(if (sectionName == "ai") R.color.blue_login else R.color.text_secondary))
        navProfileText.setTextColor(getColor(if (sectionName == "profile") R.color.blue_login else R.color.text_secondary))

        toolbarTitle.text = when (sectionName) {
            "vacancies" -> "Vacancies"
            "candidates" -> "Candidates"
            "ai" -> "AI Assistant"
            else -> "Profile"
        }
    }

    private fun setupVacancySection() {
        findViewById<MaterialButton>(R.id.btnConfigureTables).setOnClickListener {
            showConfigureTablesDialog()
        }

        findViewById<MaterialButton>(R.id.btnCreateVacancy).setOnClickListener {
            startActivity(Intent(this, CreateVacancyActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.btnApplyVacancyFilters).setOnClickListener {
            showVacancyFiltersDialog()
        }

        findViewById<MaterialCardView>(R.id.cardVacancySenior).setOnClickListener {
            startActivity(Intent(this, VacancyDetailsActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardVacancyBackend).setOnClickListener {
            Toast.makeText(this, "Открываем Middle Backend Developer", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupCandidatesSection() {
        findViewById<MaterialButton>(R.id.btnApplicantTableConfig).setOnClickListener {
            showApplicantConfigDialog()
        }

        findViewById<MaterialButton>(R.id.btnCandidateFilters).setOnClickListener {
            showCandidateFiltersDialog()
        }

        findViewById<MaterialCardView>(R.id.cardCandidateElena).setOnClickListener {
            startActivity(Intent(this, CandidateDetailsActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardCandidateAlex).setOnClickListener {
            Toast.makeText(this, "Карточка Alex Mercer открыта", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupAiSection() {
        val input = findViewById<TextInputEditText>(R.id.editTextAiMessage)
        val btnSend = findViewById<ImageButton>(R.id.btnSendAi)
        val aiUserBubble = findViewById<TextView>(R.id.tvAiUserBubble)
        val aiAssistantBubble = findViewById<TextView>(R.id.tvAiAssistantBubble)
        val aiLoadingText = findViewById<TextView>(R.id.tvAiLoading)
        val aiErrorCard = findViewById<MaterialCardView>(R.id.cardAiError)
        val btnRetry = findViewById<MaterialButton>(R.id.btnRetryAi)

        btnSend.setOnClickListener {
            val message = input.text.toString().trim()
            if (message.isEmpty()) {
                Toast.makeText(this, "Введите сообщение для AI", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            aiUserBubble.visibility = View.VISIBLE
            aiUserBubble.text = message
            aiLoadingText.visibility = View.VISIBLE
            aiAssistantBubble.visibility = View.GONE
            aiErrorCard.visibility = View.GONE

            if (message.lowercase().contains("погода")) {
                aiLoadingText.visibility = View.GONE
                aiErrorCard.visibility = View.VISIBLE
            } else {
                aiLoadingText.visibility = View.GONE
                aiAssistantBubble.visibility = View.VISIBLE
                aiAssistantBubble.text = "Я проанализировал запрос и могу помочь с краткой выжимкой по hiring funnel, статусам кандидатов и воронке откликов."
            }

            input.setText("")
        }

        btnRetry.setOnClickListener {
            aiErrorCard.visibility = View.GONE
            aiAssistantBubble.visibility = View.VISIBLE
            aiAssistantBubble.text = "Повторный ответ: погодный модуль временно недоступен, но я могу помочь с HR-аналитикой и подбором."
        }
    }

    private fun setupProfileSection() {
        findViewById<MaterialButton>(R.id.btnChangePhoto).setOnClickListener {
            showChangePhotoDialog()
        }

        findViewById<MaterialButton>(R.id.btnOpenVacancyFromProfile).setOnClickListener {
            showSection("vacancies")
        }

        findViewById<MaterialButton>(R.id.btnOpenCandidatesFromProfile).setOnClickListener {
            showSection("candidates")
        }
    }

    private fun showConfigureTablesDialog() {
        val items = arrayOf("Vacancy Title", "Position Type", "Opening Date", "Department")
        val checked = booleanArrayOf(true, true, true, true)

        AlertDialog.Builder(this)
            .setTitle("Configure Tables")
            .setMultiChoiceItems(items, checked, null)
            .setPositiveButton("Apply Changes") { _, _ ->
                Toast.makeText(this, "Настройки таблицы сохранены", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Reset to Default", null)
            .show()
    }

    private fun showVacancyFiltersDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_vacancy_filters, null)
        val editName = dialogView.findViewById<EditText>(R.id.editVacancyName)

        AlertDialog.Builder(this)
            .setTitle("Vacancy Filters")
            .setView(dialogView)
            .setPositiveButton("Apply Filters") { _, _ ->
                Toast.makeText(this, "Применены фильтры: ${editName.text}", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Reset all") { _, _ ->
                showResetFiltersConfirmation()
            }
            .show()
    }

    private fun showResetFiltersConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Reset all filters?")
            .setMessage("Все параметры фильтра будут сброшены.")
            .setPositiveButton("Reset") { _, _ ->
                Toast.makeText(this, "Фильтры сброшены", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showApplicantConfigDialog() {
        val items = arrayOf("Applicant Full Name", "Applicant Photo", "City / Location", "Desired Position", "Status")
        val checked = booleanArrayOf(true, true, true, true, true)

        AlertDialog.Builder(this)
            .setTitle("Table Configuration")
            .setMultiChoiceItems(items, checked, null)
            .setPositiveButton("Apply Changes") { _, _ ->
                Toast.makeText(this, "Колонки таблицы кандидатов обновлены", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Reset to Default", null)
            .show()
    }

    private fun showCandidateFiltersDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_candidate_filters, null)

        AlertDialog.Builder(this)
            .setTitle("Applicant Filters")
            .setView(dialogView)
            .setPositiveButton("Apply Filters") { _, _ ->
                Toast.makeText(this, "Фильтры кандидатов применены", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Reset all", null)
            .show()
    }

    private fun showChangePhotoDialog() {
        AlertDialog.Builder(this)
            .setTitle("Update Profile Photo")
            .setItems(arrayOf("Take Photo", "Choose from Gallery")) { _, which ->
                if (which == 0 || which == 1) {
                    startActivity(Intent(this, PhotoEditorActivity::class.java))
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
