package days

import Day

object Day1 : Day {
    override fun solve1(s: String): String = s.lineSequence()
        .map(String::toInt)
        .zipWithNext()
        .count { (a, b) -> b > a }
        .toString()

    override fun solve2(s: String): String = s.lineSequence()
        .map(String::toInt)
        .windowed(3, partialWindows = true)
        .map(List<Int>::sum)
        .zipWithNext()
        .count { (a, b) -> b > a }
        .toString()


}