
# Gradle-Dev-Ops Sudoku Lab

This lab makes use of gradle and kotlin to implement a Sudoku solver that allows for a text file to be inputed that contains a sudoku board.


## Main Files

**src/main/kotlin/App.kt**
- This file is the main entry point for the sudoku application.
- Loads a sudoku board.
- Creates sudoku object and triggers function.


**src/main/kotlin/Sudoku.kt**
- Core class that stores the game logic.
- Holds the 9 X 9 board internally.
- Loads board data from the text file.
- Implements the logic for checking row and printing board.

**src/test/kotlin/SudokuTest.kt**
- texts that the board loaded succesfully
- Tests if the move is valid
- Tests if there are invalid moves
- Tests if the board is complete

## Delivery

- working Kotlin code that builds successfully and can solve sudoku boards input as text files passed at the command line
- the code successfully display an error message if the board cannot be solved within a reasonable amount of time
- 3 unit tests in the codebase that Gradle runs when a user invokes ./gradlew test. (didnt use a wrapper, gradle test does the same job)
- running the build and tests in a CI pipeline using GitHub Actions
- Tagging and release 
