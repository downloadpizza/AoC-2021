package days

import Day

data class Command(val name: String, val value: Int)

object Day2 : Day {
    override fun solve1(s: String): String {
        val commands = s.trim().lineSequence().map {
            val (a, b) = it.split(" ")
            Command(a, b.toInt())
        }

        var forward = 0
        var depth = 0
        for (cmd in commands) {
            when(cmd.name) {
                "forward" -> {
                    forward += cmd.value
                }
                "up" -> {
                    depth -= cmd.value
                }
                "down" -> {
                    depth += cmd.value
                }
            }
        }

        return (forward * depth).toString()
    }

    override fun solve2(s: String): String {
        val commands = s.trim().lineSequence().map {
            val (a, b) = it.split(" ")
            Command(a, b.toInt())
        }

        var forward = 0
        var depth = 0
        var aim = 0

        for (cmd in commands) {
            when(cmd.name) {
                "forward" -> {
                    forward += cmd.value
                    depth += cmd.value * aim
                }
                "up" -> {
                    aim -= cmd.value
                }
                "down" -> {
                    aim += cmd.value
                }
            }
        }

        return (forward * depth).toString()
    }
}

private data class SubState(val forward: Int = 0, val depth: Int = 0, val aim: Int = 0)

object Day2OL : Day {
    override fun solve1(s: String): String = s.trim().lineSequence().map {
            val (a, b) = it.split(" ")
            Command(a, b.toInt())
        }.fold(SubState()) { state, cmd ->
        when(cmd.name) {
            "forward" -> {
                SubState(state.forward + cmd.value, state.depth, state.aim)
            }
            "up" -> {
                SubState(state.forward, state.depth - cmd.value, state.aim)
            }
            "down" -> {
                SubState(state.forward, state.depth + cmd.value, state.aim)
            }
            else -> error("")
        }
    }.run {
        (this.depth*this.forward).toString()
    }

    override fun solve2(s: String): String = s.trim().lineSequence().map {
        val (a, b) = it.split(" ")
        Command(a, b.toInt())
    }.fold(SubState()) { state, cmd ->
        when(cmd.name) {
            "forward" -> {
                SubState(state.forward + cmd.value, state.depth + state.aim*cmd.value, state.aim)
            }
            "up" -> {
                SubState(state.forward, state.depth, state.aim - cmd.value)
            }
            "down" -> {
                SubState(state.forward, state.depth, state.aim + cmd.value)
            }
            else -> error("")
        }
    }.run {
        (this.depth*this.forward).toString()
    }
}