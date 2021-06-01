package ru.artem.fifteenpuzzle.core.entity

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import ru.artem.fifteenpuzzle.R

class PuzzleUnit(
    resources: Resources,
    val number: Int,
    val width: Int,
    val height: Int
) {
    val bitmap: Bitmap

    init {
        when (number) {
            !in 1..24 -> throw RuntimeException("")
        }

        val notScaledBitmap = BitmapFactory.decodeResource(
            resources, when (number) {
                1 -> R.drawable.num_1
                2 -> R.drawable.num_2
                3 -> R.drawable.num_3
                4 -> R.drawable.num_4
                5 -> R.drawable.num_5
                6 -> R.drawable.num_6
                7 -> R.drawable.num_7
                8 -> R.drawable.num_8
                9 -> R.drawable.num_9
                10 -> R.drawable.num_10
                11 -> R.drawable.num_11
                12 -> R.drawable.num_12
                13 -> R.drawable.num_13
                14 -> R.drawable.num_14
                15 -> R.drawable.num_15
                16 -> R.drawable.num_16
                17 -> R.drawable.num_17
                18 -> R.drawable.num_18
                19 -> R.drawable.num_19
                20 -> R.drawable.num_20
                21 -> R.drawable.num_21
                22 -> R.drawable.num_22
                23 -> R.drawable.num_23
                24 -> R.drawable.num_24
                else -> throw RuntimeException("")
            }
        )
        bitmap = Bitmap.createScaledBitmap(notScaledBitmap, width, height, true)
    }

    operator fun compareTo(otherPuzzleUnit: PuzzleUnit): Int {
        return this.number - otherPuzzleUnit.number
    }
}