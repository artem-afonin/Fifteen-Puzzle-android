package ru.artem.fifteenpuzzle.core.entity

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.net.HttpURLConnection
import java.net.URL

object ClientRequests {
    //    const val URL = "http://127.0.0.1:5000/15puzzle/api/v1.0/leaderboard"
    const val URL = "http://d540c6ad55d3.ngrok.io/15puzzle/api/v1.0/leaderboard"

    fun postRecord(type: Int, name: String, time: Int) {
        val recordMap: MutableMap<String, String> = HashMap()
        recordMap["type"] = type.toString()
        recordMap["name"] = name
        recordMap["time"] = time.toString()

        val thread = Thread {
            val serverURL = URL(URL)
            with(serverURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json; utf-8")
                setRequestProperty("Accept", "application/json")
                doOutput = true
                val outputStream = this.outputStream
                val input: ByteArray = Gson().toJson(recordMap).toByteArray()
                outputStream.write(input, 0, input.size)
                outputStream.flush()
                Log.d("TEST", responseCode.toString())
            }
        }
        thread.start()
        thread.join()
    }

    fun getLeaderboards(): Triple<LeaderboardObject, LeaderboardObject, LeaderboardObject> {
        var leaderBoard3 = LeaderboardObject(0, mutableListOf())
        var leaderBoard4 = LeaderboardObject(0, mutableListOf())
        var leaderBoard5 = LeaderboardObject(0, mutableListOf())


        val thread = Thread {
            val serverURL = URL(URL)
            with(serverURL.openConnection() as HttpURLConnection) {
                requestMethod = "GET"
                val bufferedReader = inputStream.bufferedReader()
                val parser = JsonParser.parseReader(bufferedReader)
                val array = parser.asJsonArray
                for (i in array) {
                    val leaderBoard = i.asJsonObject
                    when (leaderBoard["type"].asInt) {
                        3 -> {
                            val tempArray: MutableList<Leader> = mutableListOf()
                            val leaderArray = leaderBoard["users"].asJsonArray
                            for (j in leaderArray) {
                                val oneOfLeader = j.asJsonObject
                                val leader =
                                    Leader(oneOfLeader["name"].asString, oneOfLeader["time"].asInt)
                                tempArray.add(leader)
                            }
                            leaderBoard3 = LeaderboardObject(leaderBoard["type"].asInt, tempArray)
                        }
                        4 -> {
                            val tempArray: MutableList<Leader> = mutableListOf()
                            val leaderArray = leaderBoard["users"].asJsonArray
                            for (j in leaderArray) {
                                val oneOfLeader = j.asJsonObject
                                val leader =
                                    Leader(oneOfLeader["name"].asString, oneOfLeader["time"].asInt)
                                tempArray.add(leader)
                            }
                            leaderBoard4 = LeaderboardObject(leaderBoard["type"].asInt, tempArray)
                        }
                        5 -> {
                            val tempArray: MutableList<Leader> = mutableListOf()
                            val leaderArray = leaderBoard["users"].asJsonArray
                            for (j in leaderArray) {
                                val oneOfLeader = j.asJsonObject
                                val leader =
                                    Leader(oneOfLeader["name"].asString, oneOfLeader["time"].asInt)
                                tempArray.add(leader)
                            }
                            leaderBoard5 = LeaderboardObject(leaderBoard["type"].asInt, tempArray)
                        }
                    }
                }
            }
        }
        thread.start()
        thread.join()
        return Triple(leaderBoard3, leaderBoard4, leaderBoard5)
    }
}