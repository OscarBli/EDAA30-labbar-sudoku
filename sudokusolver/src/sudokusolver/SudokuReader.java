package sudokusolver;

public class SudokuReader implements SudokuSolver {

	private int [][] board;
		
	public SudokuReader() {
		board= new int[9][9];
	}
	
	@Override
	public boolean solve() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCell(int row, int col, int val) throws IllegalArgumentException {
		board[row-1][col-1] = val;
	}

	@Override
	public int getCell(int row, int col) throws IllegalArgumentException {
	// TODO Auto-generated method stub
		return board
		
		return 0;
	}

}
