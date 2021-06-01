package ru.artem.fifteenpuzzle.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.artem.fifteenpuzzle.R
import ru.artem.fifteenpuzzle.core.entity.ClientRequests
import java.text.SimpleDateFormat
import java.util.*

class ScorePushActivity : AppCompatActivity() {

    @SuppressLint("SimpleDateFormat")
    val timeFormatter = SimpleDateFormat("mm:ss.S")
    val calendar = Calendar.getInstance()
    var size = -1
    var time = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.score_push_activity)

        size = intent.extras?.getInt("size")!!
        time = intent.extras?.getLong("time")!!
        initView()
    }

    fun initView() {
        val timeTextView = findViewById<TextView>(R.id.score_push_time_text_view)
        calendar.timeInMillis = time
        timeTextView.text = timeFormatter.format(calendar.time)

        val editText = findViewById<EditText>(R.id.edit_text)
        editText.requestFocus()

        val mainMenuIntent = Intent(applicationContext, MainMenuActivity::class.java)
        val submitButton = findViewById<Button>(R.id.score_push_submit_button)
        submitButton.setOnClickListener {
            val type = size
            val name = editText.text.toString()
            val time = time
            if (!name.isBlank()) {
                ClientRequests.postRecord(type, name, time.toInt())
            }
            startActivity(mainMenuIntent)
        }

        val cancelButton = findViewById<Button>(R.id.score_push_cancel_button)
        cancelButton.setOnClickListener {
            startActivity(mainMenuIntent)
        }
    }
}