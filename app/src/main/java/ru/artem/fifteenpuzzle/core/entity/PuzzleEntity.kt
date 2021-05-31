package ru.artem.fifteenpuzzle.core.entity

import android.content.res.Resources

class PuzzleEntity(resources: Resources, val size: Int) {
    private val puzzleMatrix: PuzzleMatrix

    init {
        when (size) {
            !in 3..5 -> throw RuntimeException("Incorrect size")
        }
        puzzleMatrix = PuzzleMatrix(resources, size)
    }
}