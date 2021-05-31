package ru.artem.fifteenpuzzle

import android.content.res.Resources
import org.junit.Test
import ru.artem.fifteenpuzzle.core.entity.PuzzleMatrix

class ExampleUnitTest {
    @Test
    fun puzzleMatrixInit() {
        val puzzleMatrix = PuzzleMatrix(Resources(), 5)
        println(puzzleMatrix)
        puzzleMatrix.move(1, 1)
        println(puzzleMatrix)
        puzzleMatrix.move(4, 3)
        println(puzzleMatrix)
        puzzleMatrix.move(3, 3)
        println(puzzleMatrix)
    }
}