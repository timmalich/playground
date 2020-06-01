package de.midnightghost.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuSolverTest {

    private int[][] sudokuInput = new int[][]{//
        {0,2,4,6,0,0,0,5,0},//
        {1,7,0,0,0,5,0,0,9},//
        {3,0,0,9,0,0,0,0,1},//
        {6,9,1,0,0,3,5,8,0},//
        {0,0,8,1,0,4,9,0,0},//
        {0,3,2,8,0,0,7,1,6},//
        {5,0,0,0,0,8,0,0,7},//
        {8,0,0,7,0,0,0,6,5},//
        {0,1,0,0,0,6,4,9,0} //
    };

    private int[][] sudokuSolution = new int[][]{//
            {9,2,4,6,1,7,8,5,3},
            {1,7,6,3,8,5,2,4,9},
            {3,8,5,9,4,2,6,7,1},
            {6,9,1,2,7,3,5,8,4},
            {7,5,8,1,6,4,9,3,2},
            {4,3,2,8,5,9,7,1,6},
            {5,6,3,4,9,8,1,2,7},
            {8,4,9,7,2,1,3,6,5},
            {2,1,7,5,3,6,4,9,8},
    };


    @Test
    void solve() {
        SudokuSolver sudokuSolver = new SudokuSolver();
        sudokuSolver.setSudoku(sudokuInput);
        sudokuSolver.solve();
        assertArrayEquals(sudokuSolution,  sudokuSolver.getSolution());
    }
    @Test
    void solveStatic() {
        assertArrayEquals(sudokuSolution,  SudokuSolver.solve(sudokuInput));
    }
}