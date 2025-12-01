package ie.tudublin

import java.io.File

fun printBoard(board: Array<IntArray>) {
    for (r in 0..8) {
        val line = buildString {
            for (c in 0..8) {
                val v = board[r][c]
                append(if (v == 0) '.' else v.toString())
                if (c % 3 == 2 && c != 8) append(" | ")
                else if (c != 8) append(" ")
            }
        }
        println(line)
        if (r % 3 == 2 && r != 8) println("------+-------+------")
    }
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: java -jar sudoku.jar <board-file>")
        println("Board file must contain 9 lines, each with 9 characters (digits 1-9 or . for empty).")
        return
    }

    val path = args[0]
    val file = File(path)
    if (!file.exists()) {
        println("Error: file '$path' not found.")
        return
    }

    val lines = file.readLines().filter { it.isNotBlank() }
    if (lines.size != 9) {
        println("Error: board must contain exactly 9 non-empty lines; found ${lines.size}.")
        return
    }

    val sudoku = try {
        Sudoku.fromLines(lines)
    } catch (e: Exception) {
        println("Error parsing board: ${e.message}")
        return
    }

    println("Input board:")
    printBoard(sudoku.getBoardCopy())

    val solved = sudoku.solve()
    if (!solved) {
        if (sudoku.iterationCount > sudoku.maxIterations) {
            println("Could not find a solution: exceeded max iterations (${sudoku.maxIterations}).")
        } else {
            println("Could not find a solution.")
        }
        return
    }

    println("\nSolution (iterations: ${sudoku.iterationCount}):")
    printBoard(sudoku.getBoardCopy())
}
