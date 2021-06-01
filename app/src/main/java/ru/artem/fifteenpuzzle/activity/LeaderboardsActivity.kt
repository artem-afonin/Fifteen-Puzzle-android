package ru.artem.fifteenpuzzle.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.artem.fifteenpuzzle.R
import ru.artem.fifteenpuzzle.core.entity.ClientRequests
import ru.artem.fifteenpuzzle.core.entity.Leader
import ru.artem.fifteenpuzzle.core.entity.LeaderboardObject
import java.text.SimpleDateFormat
import java.util.*

class LeaderboardsActivity : AppCompatActivity() {
    @SuppressLint("SimpleDateFormat")
    val timeFormatter = SimpleDateFormat("mm:ss.S")
    private val calendar = Calendar.getInstance()

    private val nameTextViewIdList = listOf(
        R.id.leaderboard_1_text,
        R.id.leaderboard_2_text,
        R.id.leaderboard_3_text,
        R.id.leaderboard_4_text,
        R.id.leaderboard_5_text,
    )
    private val timeTextViewIdList = listOf(
        R.id.leaderboard_1_time,
        R.id.leaderboard_2_time,
        R.id.leaderboard_3_time,
        R.id.leaderboard_4_time,
        R.id.leaderboard_5_time,
    )
    lateinit var board3: LeaderboardObject
    lateinit var board4: LeaderboardObject
    lateinit var board5: LeaderboardObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.leaderboards_activity)

        getLeaderboards()
        initButtons()

        val size = intent.extras?.getInt("size") ?: 4
        when (size) {
            3 -> loadLeaders(board3.leaders)
            4 -> loadLeaders(board4.leaders)
            5 -> loadLeaders(board5.leaders)
        }
    }

    fun getLeaderboards() {
        val leaderboards = ClientRequests.getLeaderboards()
        board3 = leaderboards.first
        board4 = leaderboards.second
        board5 = leaderboards.third
    }

    fun initButtons() {
        val button3 = findViewById<Button>(R.id.leaderboard_3x3_button)
        val button4 = findViewById<Button>(R.id.leaderboard_4x4_button)
        val button5 = findViewById<Button>(R.id.leaderboard_5x5_button)
        button3.setOnClickListener {
            clearTextViews()
            loadLeaders(board3.leaders)
        }
        button4.setOnClickListener {
            clearTextViews()
            loadLeaders(board4.leaders)
        }
        button5.setOnClickListener {
            clearTextViews()
            loadLeaders(board5.leaders)
        }
    }

    fun loadLeaders(leaders: List<Leader>) {
        val sortedLeaders = leaders.sortedBy { it.time }
        sortedLeaders.forEachIndexed { index, leader ->
            val nameTextView = findViewById<TextView>(nameTextViewIdList[index])
            val timeTextView = findViewById<TextView>(timeTextViewIdList[index])

            nameTextView.text = leader.name

            val time = sortedLeaders[index].time
            calendar.timeInMillis = time.toLong()
            timeTextView.text = timeFormatter.format(calendar.time)
        }
    }

    fun clearTextViews() {
        for (i in 0 until 5) {
            findViewById<TextView>(nameTextViewIdList[i]).text = ""
            findViewById<TextView>(timeTextViewIdList[i]).text = ""
        }
    }
}