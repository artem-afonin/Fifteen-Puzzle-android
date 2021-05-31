package ru.artem.fifteenpuzzle.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ru.artem.fifteenpuzzle.R
import kotlin.system.exitProcess

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu_activity)

        initButtons()
    }

    fun initButtons() {
        val startGameButton = findViewById<Button>(R.id.main_menu_start_game_button)
        val leaderboardsButton = findViewById<Button>(R.id.main_menu_leaderboards_button)
        val exitButton = findViewById<Button>(R.id.main_menu_exit_button)

        startGameButton.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }
        leaderboardsButton.setOnClickListener {
            startActivity(Intent(this, LeaderboardsActivity::class.java))
        }
        exitButton.setOnClickListener { exitProcess(0) }
    }
}