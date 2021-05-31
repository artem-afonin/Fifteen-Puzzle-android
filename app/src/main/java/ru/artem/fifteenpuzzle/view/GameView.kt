package ru.artem.fifteenpuzzle.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import ru.artem.fifteenpuzzle.util.Constant

class GameView : View {
    private val updaterRunnable = Runnable {
        invalidate()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.GREEN)
        handler.postDelayed(updaterRunnable, Constant.FRAME_UPDATE_RATE_MILLIS)
    }
}