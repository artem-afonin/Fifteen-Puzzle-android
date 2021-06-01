package ru.artem.fifteenpuzzle

import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.junit.Test
import ru.artem.fifteenpuzzle.core.entity.PuzzleMatrix

class ExampleUnitTest {
//    @Test
//    fun puzzleMatrixInit() {
//        val puzzleMatrix = PuzzleMatrix(Resources(), 5)
//        println(puzzleMatrix)
//        puzzleMatrix.move(1, 1)
//        println(puzzleMatrix)
//        puzzleMatrix.move(4, 3)
//        println(puzzleMatrix)
//        puzzleMatrix.move(3, 3)
//        println(puzzleMatrix)
//    }

    data class Leader(val name: String, val time: Int)
    data class LeaderboardObject(val type: Int, val leaders: List<Leader>)

    @Test
    fun gsonParse() {
        val parsedString = "[\n" +
                "  {\n" +
                "    \"type\": 3,\n" +
                "    \"users\": [\n" +
                "      {\n" +
                "        \"name\": \"Artem\",\n" +
                "        \"time\": 120\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Danil\",\n" +
                "        \"time\": 100\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Kirill\",\n" +
                "        \"time\": 80\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"type\": 4,\n" +
                "    \"users\": [\n" +
                "      {\n" +
                "        \"name\": \"Artem\",\n" +
                "        \"time\": 220\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Danil\",\n" +
                "        \"time\": 200\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Kirill\",\n" +
                "        \"time\": 180\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"type\": 5,\n" +
                "    \"users\": [\n" +
                "      {\n" +
                "        \"name\": \"Artem\",\n" +
                "        \"time\": 320\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Danil\",\n" +
                "        \"time\": 300\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Kirill\",\n" +
                "        \"time\": 380\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]"
//        val array = parser.asJsonArray
//        for (i in 0 until array.size()) {
//            val leaderboard = array.get(i).asJsonObject
//            val type = leaderboard.get("type").asInt
//            val users = leaderboard.get("users").asJsonArray
//            for (j in 0 until users.size()) {
//                val userObj = users.get(i).asJsonObject
//                val name = userObj.get("name").asString
//                val time = userObj.get("time").asInt
//                println(name)
//                println(time)
//            }
//        }

    }
}