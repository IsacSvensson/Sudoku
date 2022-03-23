import game.Board;
import logic.Solver;

public class Main {
    public static void main(String[] args) {
        Board sudoku = new Board();
        sudoku.readSudokuString("000000000000000000000000028000950000086000200020600750000000476070045000008009000");
        sudoku.printBoard();
        Solver solver = new Solver(sudoku.getBoard());
        System.out.println(solver.findSolutions());
        sudoku.readSudokuString(solver.solve());
        sudoku.printBoard();
    }
}
