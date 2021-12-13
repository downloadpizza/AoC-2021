package days

import Day

object Day12 : Day {
    override fun solve1(s: String): String {
        val paths = s.lineSequence().flatMap {
            val (a,b) = it.split("-")
            listOf(Pair(a,b), Pair(b,a))
        }

        val connections: Map<String, List<String>> = paths.groupBy({it.first}) {it.second}

        fun visitCave(cave: String): Int {
            fun _visitCave(cave: String, visited: List<String>): Int {
                if(cave == "end") return 1

                val visited = visited.toMutableList()
                visited.add(cave)
                val next = connections[cave]!!.filterNot {
                    it in visited && it.all(Char::isLowerCase)
                }

                return next.sumOf { nextCave ->
                    _visitCave(nextCave, visited)
                }
            }
            return _visitCave(cave, listOf())
        }

        return visitCave("start").toString()
    }

    override fun solve2(s: String): String {
        val paths = s.lineSequence().flatMap {
            val (a,b) = it.split("-")
            listOf(Pair(a,b), Pair(b,a))
        }

        val connections: Map<String, List<String>> = paths.filterNot { it.second == "start" }.groupBy({it.first}) {it.second}

        fun visitCave(cave: String): Int {
            fun _visitCave(cave: String, state: Pair<List<String>, Boolean>): Int {
                if(cave == "end") return 1
                var (visitedLast, smallDouble) = state

                if(cave != "start" && cave.all(Char::isLowerCase) && cave in state.first)
                    smallDouble = true

                val visited = visitedLast.toMutableList()
                visited.add(cave)
                val next = connections[cave]!!.filterNot {
                    it in visited && it.all(Char::isLowerCase) && smallDouble
                }

                return next.sumOf { nextCave ->
                    _visitCave(nextCave, Pair(visited, smallDouble))
                }
            }
            return _visitCave(cave, Pair(listOf(), false))
        }

        return visitCave("start").toString()
    }
}