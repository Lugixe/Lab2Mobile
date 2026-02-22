package ru.mobile.mob2lab

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Элементы UI
    private lateinit var ivArtwork: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvAuthor: TextView
    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button

    // Данные
    private val artworks = listOf(
        Artwork(R.drawable.pic1, R.string.art1_title, R.string.art1_author, R.string.art1_desc),
        Artwork(R.drawable.pic2, R.string.art2_title, R.string.art2_author, R.string.art2_desc),
        Artwork(R.drawable.pic3, R.string.art3_title, R.string.art3_author, R.string.art3_desc)
    )

    // Текущее состояние (индекс)
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Инициализация View
        ivArtwork = findViewById(R.id.ivArtwork)
        tvTitle = findViewById(R.id.tvTitle)
        tvAuthor = findViewById(R.id.tvAuthor)
        btnPrevious = findViewById(R.id.btnPrevious)
        btnNext = findViewById(R.id.btnNext)

        // Безопасные зоны (отступы)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Восстановление состояния при повороте
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("KEY_INDEX", 0)
        }

        updateUI()

        // Слушатели нажатий
        btnPrevious.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateUI()
            }
        }

        btnNext.setOnClickListener {
            if (currentIndex < artworks.size - 1) {
                currentIndex++
                updateUI()
            }
        }
    }

    /**
     * Метод для сохранения состояния (поворот экрана)
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("KEY_INDEX", currentIndex)
    }

    /**
     * Обновление интерфейса на основе currentIndex
     */
    private fun updateUI() {
        val currentArt = artworks[currentIndex]

        // Установка картинки и текста
        ivArtwork.setImageResource(currentArt.imageRes)
        tvTitle.setText(currentArt.titleRes)
        tvAuthor.setText(currentArt.authorRes)

        // Accessibility: описание для TalkBack
        ivArtwork.contentDescription = getString(currentArt.descriptionRes)

        // Логика блокировки кнопок
        btnPrevious.isEnabled = currentIndex > 0
        btnNext.isEnabled = currentIndex < artworks.size - 1
    }
}