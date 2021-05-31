package ru.artem.fifteenpuzzle.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.artem.fifteenpuzzle.R

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
    }
}