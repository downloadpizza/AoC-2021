import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import days.*
import java.io.File

val days = mapOf(
    1 to Day1,
    2 to Day2OL,
    3 to Day3,
    4 to Day4,
    5 to Day5,
    6 to Day6OL,
    7 to Day7,
    8 to Day8,
    9 to Day9,
    10 to Day10,
    11 to Day11,
    12 to Day12,
    13 to Day13OL
)

fun main() {
    val day = 13

    val input = cachedInput(day).trim()

    val solution = days[day] ?: error("Solution for day $day does not yet exist")

    println(solution.solve1(input))

    println(solution.solve2(input))
}

fun cachedInput(day: Int): String {
    File("inputs").mkdir()
    val input = File("inputs/day$day")
    return if (input.exists()) {
        input.readText()
    } else {
        val url = "https://adventofcode.com/2021/day/$day/input"
        val (_, _, result) = Fuel.get(url)
            .header("Cookie", "session=${Config.session}")
            .responseString()
        if (result is Result.Failure) {
            throw result.error
        }

        val body = result.get()
        input.createNewFile()
        input.writeText(body)
        body
    }
}