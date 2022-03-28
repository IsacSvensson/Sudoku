import game.Board;
import gameGenerator.Generator;
import logic.Solver;

public class Main {
    public static void main(String[] args) {
        Generator gameGenerator = new Generator();
        Board sudoku = new Board(gameGenerator.generateGame());
        sudoku.printBoard();
        Solver solver = new Solver(sudoku.getBoard());
        System.out.println(solver.findSolutions());
        sudoku.readSudokuString(solver.solve());
        sudoku.printBoard();
    }
}
