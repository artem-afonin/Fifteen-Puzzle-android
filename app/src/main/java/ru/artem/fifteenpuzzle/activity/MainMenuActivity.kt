package ru.artem.fifteenpuzzle.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import ru.artem.fifteenpuzzle.R
import kotlin.system.exitProcess

class MainMenuActivity : AppCompatActivity() {
    lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu_activity)

        initButtons()
    }

    fun initButtons() {
        val startGameButton = findViewById<Button>(R.id.main_menu_start_game_button)
        val leaderboardsButton = findViewById<Button>(R.id.main_menu_leaderboards_button)
        val exitButton = findViewById<Button>(R.id.main_menu_exit_button)
        radioGroup = findViewById(R.id.radioGroup)

        startGameButton.setOnClickListener {
            val gameActivityIntent = Intent(this, GameActivity::class.java)
            val gameSize = getGameSize()
            gameActivityIntent.putExtra("size", gameSize)
            startActivity(gameActivityIntent)
        }
        leaderboardsButton.setOnClickListener {
            val leaderboardActivityIntent = Intent(this, LeaderboardsActivity::class.java)
            val gameSize = getGameSize()
            leaderboardActivityIntent.putExtra("size", gameSize)
            startActivity(leaderboardActivityIntent)
        }
        exitButton.setOnClickListener { exitProcess(0) }
    }

    fun getGameSize() = when (radioGroup.checkedRadioButtonId) {
        R.id.x_3_radio_button -> 3
        R.id.x_4_radio_button -> 4
        R.id.x_5_radio_button -> 5
        else -> throw RuntimeException("Invalid game size")
    }
}