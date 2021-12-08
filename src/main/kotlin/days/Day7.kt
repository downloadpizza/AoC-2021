package days

import Day
import kotlin.math.absoluteValue

object Day7 : Day {
    override fun solve1(s: String): String {
        val crabs = s.trim().split(",").map(String::toInt)
        val avg = crabs.sorted()[crabs.size/2]
        println(crabs.sumOf { (it - avg).absoluteValue })
        return (crabs.minOrNull()!!..crabs.maxOrNull()!!).minOf { dst ->
            crabs.sumOf { crab ->
                (crab - dst).absoluteValue
            }
        }.toString()
    }

    override fun solve2(s: String): String {
        val crabs = s.trim().split(",").map(String::toInt)
        return (crabs.minOrNull()!!..crabs.maxOrNull()!!).minOf { dst ->
            crabs.sumOf { crab ->
                val dist = (crab - dst).absoluteValue
                (1..dist).sum()
            }
        }.toString()
    }
}

object Day7OL : Day {
override fun solve1(s: String): String = s.trim().split(",")
    .map(String::toInt)
    .run {
        (this.minOrNull()!!..this.maxOrNull()!!).minOf { dst ->
            this.sumOf { crab ->
                (crab - dst).absoluteValue
            }
        }
    }.toString()

    override fun solve2(s: String): String = s.trim().split(",")
        .map(String::toInt)
        .run {
            (this.minOrNull()!!..this.maxOrNull()!!).minOf { dst ->
                this.sumOf { crab ->
                    (1..(crab - dst).absoluteValue).sum()
                }
            }
        }.toString()
}