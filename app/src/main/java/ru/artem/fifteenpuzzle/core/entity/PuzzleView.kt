package ru.artem.fifteenpuzzle.core.entity

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

class PuzzleView(
    view: View,
    val size: Int
) {
    private val puzzleMatrix: PuzzleMatrix
    private val widthStep: Int
    private val heightStep: Int
    private val paint = Paint()

    init {
        when (size) {
            !in 3..5 -> throw RuntimeException("Incorrect size")
        }
        widthStep = view.width / size
        heightStep = view.height / size
        puzzleMatrix = PuzzleMatrix(view.resources, size, widthStep, heightStep)
    }

    fun draw(canvas: Canvas) {
        for (i in 0 until size) {
            for (j in 0 until size) {
                val puzzleUnit = puzzleMatrix.matrix[i][j]
                puzzleUnit?.let {
                    canvas.drawBitmap(
                        it.bitmap,
                        (j * widthStep).toFloat(),
                        (i * heightStep).toFloat(),
                        paint
                    )
                }
            }
        }
    }

    fun processCollision(x: Float, y: Float) {
        for (i in 0 until size) {
            for (j in 0 until size) {
                val puzzleUnit = puzzleMatrix.matrix[i][j]
                puzzleUnit?.let {
                    val tapRect = Rect(x.toInt(), y.toInt(), x.toInt(), y.toInt())
                    val puzzleUnitRect = Rect(
                        j * widthStep,
                        i * heightStep,
                        j * widthStep + it.width,
                        i * heightStep + it.height
                    )
                    if (puzzleUnitRect.intersect(tapRect)) {
                        puzzleMatrix.move(i, j)
                    }
                }
            }
        }
    }

    fun isSolved(): Boolean {
        return puzzleMatrix.isSolved()
    }
}