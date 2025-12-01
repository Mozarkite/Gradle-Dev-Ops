package ie.tudublin

class Sudoku(initialBoard: Array<IntArray>) {

    val board: Array<IntArray> = Array(9) { r -> IntArray(9) { c -> initialBoard[r][c] } }

    var iterationCount: Long = 0
        private set

    var maxIterations: Long = 2_000_000L

    fun solve(): Boolean {
        iterationCount = 0
        return solveRecursive()
    }

    private fun solveRecursive(): Boolean {
        iterationCount++
        if (iterationCount > maxIterations) return false

        //find first empty cell
        for (r in 0..8) {
            for (c in 0..8) {
                if (board[r][c] == 0) {
                    for (valCandidate in 1..9) {
                        if (isValid(r, c, valCandidate)) {
                            board[r][c] = valCandidate
                            if (solveRecursive()) return true
                            board[r][c] = 0
                        }
                    }
                    // no candidate works then return false
                    return false
                }
            }
        }
        //no empty cells means its solved
        return true
    }

    private fun isValid(row: Int, col: Int, value: Int): Boolean {
        // Check row and column
        for (i in 0..8) {
            if (board[row][i] == value) return false
            if (board[i][col] == value) return false
        }
        // Check 3x3 box
        val br = (row / 3) * 3
        val bc = (col / 3) * 3
        for (r in br until br + 3) {
            for (c in bc until bc + 3) {
                if (board[r][c] == value) return false
            }
        }
        return true
    }

    //Helper to get a deep copy of the solved board
    fun getBoardCopy(): Array<IntArray> {
        return Array(9) { r -> IntArray(9) { c -> board[r][c] } }
    }

    companion object {
        //helper to parse from 9 lines of text with digits and dots/spaces for zeros
        fun fromLines(lines: List<String>): Sudoku {
            require(lines.size == 9) { "Expected 9 lines for a Sudoku board, got ${lines.size}" }
            val arr = Array(9) { IntArray(9) }
            for (r in 0..8) {
                val row = lines[r].trim().replace("\\s+".toRegex(), "")
                require(row.length == 9) { "Each line must contain 9 digits or . characters" }
                for (c in 0..8) {
                    arr[r][c] = when (val ch = row[c]) {
                        '.', '0' -> 0
                        in '1'..'9' -> ch - '0'
                        else -> throw IllegalArgumentException("Invalid character '$ch' in input")
                    }
                }
            }
            return Sudoku(arr)
        }
    }
}
