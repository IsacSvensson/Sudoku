package game;

public class Board {
    public static final int BOARD_SIZE = 9;
    private final Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];

    public Board(){
        for (int y = 0; y<BOARD_SIZE; y++)
            for (int x = 0; x<BOARD_SIZE; x++){
                board[y][x] = new Cell(0);
            }
    }

    public void readSudokuString(String sudoku){
        if (sudoku.length() != 81)
            return;

        for (int i = 0; i<81; i++){
            setCell(i%9, i/9, Character.getNumericValue(sudoku.charAt(i)));
        }
    }

    public void setCell(int x, int y, int value){
        board[y][x].setValue(value);
    }
    public Cell getCell(int x, int y){
        return board[y][x];
    }

    public void printBoard(){
        String border = "+---------+---------+---------+";
        System.out.println(border);
        printRows(0,3);
        System.out.println(border);
        printRows(3,6);
        System.out.println(border);
        printRows(6,9);
        System.out.println(border);
    }

    private void printRows(int start, int end) {
        for (int y = start; y<end; y++)
            for (int x = 0; x<=BOARD_SIZE; x++){
                if (x%3 == 0){
                    System.out.print('|');
                }
                if (x == 9){
                    System.out.print('\n');
                    break;
                }
                if (board[y][x].getValue() == 0){
                    System.out.print("   ");
                } else {
                    System.out.print(" ");
                    System.out.print(board[y][x].getValue());
                    System.out.print(" ");
                }
            }
    }
    public int[][] getBoard(){
        int[][] board = new int[9][9];

        for (int y = 0; y<9; y++)
            for (int x = 0; x<9; x++)
                board[y][x] = getCell(x,y).getValue();

        return board;
    }
}
