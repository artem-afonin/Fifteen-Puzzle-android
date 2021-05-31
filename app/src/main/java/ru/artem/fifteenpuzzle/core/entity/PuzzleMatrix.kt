package ru.artem.fifteenpuzzle.core.entity

import android.content.res.Resources
import kotlin.math.abs

class PuzzleMatrix(resources: Resources, val size: Int) {
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
                row.add(PuzzleUnit(resources, index))
            }
            matrix.add(row)
        }
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