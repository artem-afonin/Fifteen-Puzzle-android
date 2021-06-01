package ru.artem.fifteenpuzzle.core.entity

import java.io.Serializable

data class LeaderboardObject(val type: Int, val leaders: MutableList<Leader>) : Serializable