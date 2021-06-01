package ru.artem.fifteenpuzzle.core.entity

import android.content.res.Resources
import kotlin.math.abs
import kotlin.random.Random

class PuzzleMatrix(
    resources: Resources,
    val size: Int,
    width: Int,
    height: Int
) {
    val matrix: MutableList<MutableList<PuzzleUnit?>> = mutableListOf()
    lateinit var nullUnitIndex: Pair<Int, Int>

    init {
        for (i in 0 until size) {
            val row = mutableListOf<PuzzleUnit?>()
            for (j in 0 until size) {
                val index = i * size + j + 1
                if (index == size * size) {
                    row.add(null)
                    nullUnitIndex = Pair(i, j)
                    break
                }
                row.add(
                    PuzzleUnit(
                        resources,
                        index,
                        width,
                        height
                    )
                )
            }
            matrix.add(row)
        }

        shuffle()
    }

    fun move(row: Int, column: Int) {
        val isValidArgs = when {
            row in 0 until size && column in 0 until size -> true
            else -> false
        }
        if (!isValidArgs) {
            return
        }

        val nullX = (nullUnitIndex.first * size + nullUnitIndex.second) % size
        val nullY = (nullUnitIndex.first * size + nullUnitIndex.second) / size
        val x = (row * size + column) % size
        val y = (row * size + column) / size

        if ((abs(nullY - y)) + (abs(nullX - x)) == 1) {
            val nullUnit = matrix[nullUnitIndex.first][nullUnitIndex.second]
            matrix[nullUnitIndex.first][nullUnitIndex.second] = matrix[row][column]
            matrix[row][column] = nullUnit
            nullUnitIndex = Pair(row, column)
        }
    }

    private fun shuffle() {
        for (i in 1..4096) {
            val row = Random.nextInt(0, size)
            val col = Random.nextInt(0, size)
            move(row, col)
        }
    }

    fun isSolved(): Boolean {
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (matrix[i][j] == null) {
                    continue
                }
                val positionNumber = i * size + j + 1
                if (positionNumber != matrix[i][j]?.number) {
                    return false
                }
            }
        }
        return true
    }

    override fun toString(): String {
        val builder = StringBuilder()
        for (row in matrix) {
            for (unit in row) {
                builder.append("%7s".format(unit?.number))
            }
            builder.append("\n")
        }

        return builder.toString()
    }
}