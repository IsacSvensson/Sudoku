package gameGenerator;


import game.Cell;
import logic.Solver;

import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    public int[][] generateGame(){
        int[][] board = new int[9][9];
        int x = 0, y = 0;
        for (; y<9; y++)
            for (; x<9; x++){
                board[y][x] = 0;
            }

        Solver solver = new Solver(board);
        int numberOfSeeders = 0;
        int value;
        y = 0;

        solver.solve();

        int lastNumber = 0;
        while (solver.findSolutions() == 1) {
            x = ThreadLocalRandom.current().nextInt(0, 9);
            y = ThreadLocalRandom.current().nextInt(0, 9);
            lastNumber = board[y][x];
            board[y][x] = 0;
        }
        board[y][x] = lastNumber;

        return board;
    }
    private boolean inRow(int[][] board ,int row , int value){
        for (int x=0; x<9; x++)
            if (board[row][x] == value)
                return true;
        return false;
    }

}
