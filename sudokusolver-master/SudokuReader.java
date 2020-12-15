

public class SudokuReader implements SudokuSolver {

	private int [][] board;
	private final int EMPTY = 0;
		
	public SudokuReader() {
		board= new int[9][9];
	}
	
	@Override
	public boolean solve() {
		
		
		return solver(board, 0,0);
		/**for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				int boardPos = board[r][c];
					for(int value = 1; value <= 9; value++) {
						if(validCell(r, c, value)) {
							setCell(r, c, value);
							if(solve()) {
								return true;
							}
							else
							{
								board[r][c] = EMPTY;
							}
								
					}
				}
			}
			return false;
		}
		return true;
		*/
		}
	
		private boolean solver(int grid[][], int row, int col){
			//räknar 1-10;
			for (int i =1; i<10; i++){
				//om nuvarande index går att sätta in, sätt in och kolla nästa plats.
				if(validCell(row, col, i)){
					board[row][col]=i;
					if(solver(board, row, col+1)){
						return true;
					}
				}
				board[row][col] = 0;
			}
			return false;
		}

	public boolean validCell(int row, int col, int value) {
		for(int i = 0; i < 8; i++) {
			if(board[row][i] == value || board[i][col] == value) { // kollar s� den inte finns horisontellt eller vertikalt
				System.out.println("Number Already exists within the row or col");
				return false;
			}
			/*B�rjar i starten av varje "liten" ruta */
			int smallRowStart = (row/3) * 3; 
			int smallColStart = (col/3) * 3;
			
			/*iterera genom den lilla rutan, returnera false om value redan finns*/
			
			for(int r = smallRowStart; r < smallRowStart; r++) {
				for(int c = smallColStart; c < smallColStart; c++) {
					if(board[r][c] == value) {
						System.out.println("Number already exists within the small square");
						return false;
					}
				}
			}
		}
		
		
		return true;
	}
		

	@Override
	public void setCell(int row, int col, int val) throws IllegalArgumentException {
		board[row][col] = val;
	}

	@Override
	public int getCell(int row, int col) throws IllegalArgumentException {
	// TODO Auto-generated method stub
		return board[row][col];
		
	}
	
	
	public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 9; r++) {
            sb.append("\n");
            for (int c = 0; c < 9; c++) {
                sb.append(String.format(" %s ", board[r][c]));
            }
        }
        return sb.toString();
    }
	

	
	
public static void main(String[] args) {
	SudokuReader SR = new SudokuReader();
	
	System.out.print(SR.toString());
	System.out.println();
	System.out.println("______________________________");
	
	SR.setCell(1, 4, 8);
	System.out.println(SR.toString());


}
}
