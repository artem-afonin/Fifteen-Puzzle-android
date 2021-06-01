package ru.artem.fifteenpuzzle.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.artem.fifteenpuzzle.R
import ru.artem.fifteenpuzzle.view.GameView

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)

        val gameView = findViewById<GameView>(R.id.game_view)
        gameView.gameFieldSize = intent.extras?.getInt("size") ?: -1

        val timerTextView = findViewById<TextView>(R.id.timer_text_view)
        gameView.timerTextView = timerTextView
    }
}