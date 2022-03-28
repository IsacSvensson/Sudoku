package logic;

import game.Board;

import java.util.Arrays;
import java.util.Random;

public class Solver {
    int[][] board;
    public Solver(int[][] board){
        this.board = board;
    }
    public boolean multipleSolutionsExist(){
        int[] solutions = new int[1];

        countSolutions(solutions, 2);

        return solutions[0] > 1;
    }

    public int findSolutions(){
        int[] solutions = new int[1];

        countSolutions(solutions, Integer.MAX_VALUE);

        return solutions[0];
    }

    private boolean countSolutions(int[] solutions, int maxValue){
        int[] emptyCell = findEmptyCell();
        if (emptyCell == null){
            solutions[0]++;
            return true;
        }
        int x = emptyCell[0];
        int y = emptyCell[1];

        int[] possibleValues;
        possibleValues = getPossibleValue(x, y);

        for (int number: possibleValues) {
            board[y][x] = number;
            countSolutions(solutions, maxValue);
            board[y][x] = 0;
            if (solutions[0] >= maxValue){
                return true;
            }
        }

        return false;
    }

    public String solve(){
        solveSudoku();

        String solvedGame = "";
        for (int y = 0; y<9; y++)
            for (int x = 0; x<9; x++){
                solvedGame = solvedGame + (char)(board[y][x]+'0');
            }
        return solvedGame;
    }

    private boolean solveSudoku(){
        int[] emptyCell = findEmptyCell();
        if (emptyCell == null){
            return true;
        }
        int x = emptyCell[0];
        int y = emptyCell[1];

        int[] possibleValues;
        possibleValues = getPossibleValue(x, y);

        for (int number: possibleValues) {
            board[y][x] = number;

            if (solveSudoku() == true)
                return true;
            board[y][x] = 0;
        }

        return false;
    }
    private int[] findEmptyCell(){
        int[] coordinates;
        for (int y = 0; y<9; y++)
            for (int x = 0; x<9; x++)
                if (board[y][x] == 0) {
                    coordinates = new int[2];
                    coordinates[0] = x;
                    coordinates[1] = y;
                    return coordinates;
                }
        return null;
    }

    private int[] getPossibleValue(int x, int y){
        boolean possibleValues[];
        possibleValues = new boolean[9];
        Arrays.fill(possibleValues, true);

        searchBox(x,y,possibleValues);
        searchRow(y,possibleValues);
        searchColumn(x,possibleValues);

        int count = 0;
        for (int i =0; i <9; i++){
            if (possibleValues[i] == true)
                count++;
        }

        int[] possibleNumbers;
        possibleNumbers = new int[count];

        count = 0;
        for (int i =0; i <9; i++){
            if (possibleValues[i] == true) {
                possibleNumbers[count] = i + 1;
                count++;
            }
        }
        shuffleArray(possibleNumbers);

        return possibleNumbers;
    }

    private void searchBox(int x, int y, boolean[] possibleValues){
        int xStart = x/3*3;
        int yStart = y/3*3;

        boolean[] possibleValuesInBox = new boolean[9];

        for (int number = 0; number < 9; number++){
            possibleValuesInBox[number] = true;
            for (int xBox = xStart; xBox < xStart+3; xBox++)
                for (int yBox = yStart; yBox < yStart+3; yBox++){
                    if (board[yBox][xBox] == number+1) {
                        possibleValuesInBox[number] = false;
                        break;
                    }
                }
            possibleValues[number] &= possibleValuesInBox[number];
        }

    }

    private void searchColumn(int x, boolean[] possibleValues){
        boolean[] possibleValuesInColumn = new boolean[9];

        for (int number = 0; number < 9; number++){
            possibleValuesInColumn[number] = true;
            for (int y = 0; y < 9; y++)
                if (board[y][x] == number+1)
                    possibleValuesInColumn[number] = false;
            possibleValues[number] &= possibleValuesInColumn[number];
        }
    }

    private void searchRow(int y, boolean[] possibleValues){
        boolean[] possibleValuesInRow = new boolean[9];

        for (int number = 0; number < 9; number++){
            possibleValuesInRow[number] = true;
            for (int x = 0; x < 9; x++)
                if (board[y][x] == number+1)
                    possibleValuesInRow[number] = false;
            possibleValues[number] &= possibleValuesInRow[number];
        }
    }
    private void shuffleArray(int[] arr){
        Random rand = new Random();

        for (int i = 0; i < arr.length; i++) {
            int randomIndexToSwap = rand.nextInt(arr.length);
            int temp = arr[randomIndexToSwap];
            arr[randomIndexToSwap] = arr[i];
            arr[i] = temp;
        }
    }

}
