package ru.artem.fifteenpuzzle.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import ru.artem.fifteenpuzzle.activity.ScorePushActivity
import ru.artem.fifteenpuzzle.core.entity.PuzzleView
import ru.artem.fifteenpuzzle.util.Constant
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SetTextI18n")
class GameView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    var gameFieldSize: Int = -1
    lateinit var timerTextView: TextView

    @SuppressLint("SimpleDateFormat")
    val timeFormatter = SimpleDateFormat("mm:ss.S")
    val calendar = Calendar.getInstance()
    val startTime = System.currentTimeMillis()
    var currentTime = 0L

    var puzzleView: PuzzleView? = null
    private val updaterRunnable = Runnable {
        if (puzzleView == null) {
            puzzleView = PuzzleView(this, gameFieldSize)
        }
        currentTime = System.currentTimeMillis() - startTime
        invalidate()
    }

    fun exitActivity() {
        val scorePushIntent = Intent(context, ScorePushActivity::class.java)
        scorePushIntent.putExtra("size", gameFieldSize)
        scorePushIntent.putExtra("time", currentTime)
        context.startActivity(scorePushIntent)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawTime()
        canvas?.let { puzzleView?.draw(it) }
        handler.postDelayed(updaterRunnable, Constant.FRAME_UPDATE_RATE_MILLIS)
    }

    fun drawTime() {
        calendar.timeInMillis = currentTime
        timerTextView.text = timeFormatter.format(calendar.time)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            puzzleView?.processCollision(event.x, event.y)
            if (puzzleView?.isSolved() == true) {
                exitActivity()
            }
        }
        return true
    }
}