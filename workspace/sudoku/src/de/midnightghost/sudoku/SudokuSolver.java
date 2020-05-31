package de.midnightghost.sudoku;

public class SudokuSolver {
    static int[][] sudoku;
    static int[][] solution;

    public static void printGrid(int[][] grid){
        for(int x=0; x<9; x++){
            for(int y=0; y<9; y++){
                System.out.print(grid[x][y] + ",");
            }
            System.out.println();
        }
    }

    public static boolean isPossible(int x, int y, int n){
        for(int i=0; i<9; i++){
            if(sudoku[x][i] == n){
                return false;
            }
        }
        for(int i=0; i<9; i++){
            if(sudoku[i][y] == n){
                return false;
            }
        }
        int x0 = Math.floorDiv(x, 3)*3;
        int y0 = Math.floorDiv(y, 3)*3;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(sudoku[x0+i][y0+j] == n){
                    return false;
                }
            }
        }

        return true;
    }

    public static void solve(){
        for(int x=0; x<9; x++){
            for(int y=0; y<9; y++){
                if(sudoku[x][y] == 0){
                    for(int n=1; n<10; n++){
                        if(isPossible(x,y,n)){
                            sudoku[x][y] = n;
                            solve();
                            //System.out.println(String.format("(%s,%s with %s is deadend)",x,y,n));
                            sudoku[x][y] = 0;
                        }
                    }
                    return;
                }
            }
        }
        //printGrid();
        solution = deepCopy(sudoku);
    }

    private static int[][] deepCopy(int[][] src){
        int[][]trg = new int[src.length][src.length];
        for(int x=0; x<9; x++){
            trg[x] = src[x].clone();
            //for(int y=0; y<9; y++){
            //    trg[x][y] = src[x][y];
            //}
        }
        return trg;
    }


    public static void main(String[] args){
        sudoku = new int[][]{//
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
        System.out.println("Going to solve this: ");
        printGrid(sudoku);
        System.out.println("Solution is: ");
        solve();
        printGrid(solution);

    }
}
