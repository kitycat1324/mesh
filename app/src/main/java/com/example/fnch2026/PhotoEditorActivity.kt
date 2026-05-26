package com.example.fnch2026

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

// Упрощенный экран изменения фотографии профиля.
class PhotoEditorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_editor)

        findViewById<ImageButton>(R.id.btnBackPhotoEditor).setOnClickListener {
            finish()
        }

        findViewById<MaterialButton>(R.id.btnRetakePhoto).setOnClickListener {
            Toast.makeText(this, "Открыть выбор новой фотографии", Toast.LENGTH_SHORT).show()
        }

        findViewById<MaterialButton>(R.id.btnApplyPhoto).setOnClickListener {
            Toast.makeText(this, "Profile photo updated", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
