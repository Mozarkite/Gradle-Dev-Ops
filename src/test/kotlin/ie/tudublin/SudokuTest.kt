package ie.tudublin

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SudokuTest {

    //Helper to create board from 9-line representation like in file
    private fun boardOf(vararg lines: String): Array<IntArray> {
        val sudoku = Sudoku.fromLines(lines.toList())
        return sudoku.getBoardCopy()
    }

    @Test
    fun `solves a simple puzzle`() {
        val lines = arrayOf(
            "53..7....",
            "6..195...",
            ".98....6.",
            "8...6...3",
            "4..8.3..1",
            "7...2...6",
            ".6....28.",
            "...419..5",
            "....8..79"
        )
        val s = Sudoku.fromLines(lines.toList())
        val solved = s.solve()
        assertTrue(solved)
        //check solved board contains no zeros
        val solvedBoard = s.getBoardCopy()
        for (r in 0..8) for (c in 0..8) assertTrue(solvedBoard[r][c] in 1..9)
    }

    @Test
    fun `solves another known puzzle`() {
        val lines = arrayOf(
            "..3.2.6..",
            "9..3.5..1",
            "..18.64..",
            "..81.29..",
            "7.......8",
            "..67.82..",
            "..26.95..",
            "8..2.3..9",
            "..5.1.3.."
        )
        val s = Sudoku.fromLines(lines.toList())
        assertTrue(s.solve())
        val board = s.getBoardCopy()
        //each row sums to 45
        for (r in 0..8) {
            assertEquals(45, board[r].sum())
        }
    }

    @Test
    fun `unsolvable puzzle detected`() {
        //invalid board- two 5s in first row
        val lines = arrayOf(
            "553..7...", // invalid there are two 5s in a row
            "6..195...",
            ".98....6.",
            "8...6...3",
            "4..8.3..1",
            "7...2...6",
            ".6....28.",
            "...419..5",
            "....8..79"
        )
        val s = Sudoku.fromLines(lines.toList())
        //Even if we allow maxIterations default,it should not find solution
        val solved = s.solve()
        assertFalse(solved)
    }
}
