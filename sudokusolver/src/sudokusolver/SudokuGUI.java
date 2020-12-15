package sudokusolver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SudokuGUI extends JPanel {
	public JFrame frame = new JFrame("Sudoku Solver");
	public JTextField textField[][];
	public JButton solveButton;
	public JButton clearButton;
	private final int EMPTY = 0;
	private SudokuSolver sr;
	

	/* Konstruktor, innehåller rutorna, en sudokureader. Kalllar på metod för att sätta upp GUIN*/
	public SudokuGUI() {
		textField = new JTextField[9][9];
		sr = new SudokuReader();
		setGUI();
		
	}
	
	

	public void setGUI() {
		
		Random rand = new Random();
		
		Color[] col = new Color[9];
		/*Random färger för varje ny ruta*/
		for(int i = 0; i <9; i++) {
			int randColNbr = rand.nextInt(255);
			Color randCol = Color.getHSBColor(randColNbr, randColNbr, randColNbr);
			col[i] = randCol;
		}
		
		
		
		//rutorna
		frame = new JFrame("SUDOKU SOLVER");
		
		
		//lägger in rutorna och fixar designen för dem
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				textField[i][j] = new JTextField();
				textField[i][j].setBounds(60+j*40, 50+i*40, 30, 30); // dimensioner för rutorna, x y width heoght
				textField[i][j].setBackground(col[(i/3)*3+(j/3)]); //bakgrundsfärgen
				Font textFont = new Font("SansSerif", Font.BOLD, 20);
				textField[i][j].setFont(textFont);
				textField[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				textField[i][j].setText("");
				frame.add(textField[i][j]);
			}
			
			/* Lägger till solveknappen & clearknappen, kallar på solve-metoden via
			 * en actionlistener */
			solveButton = new JButton ("SOLVE");
			clearButton = new JButton("CLEAR");

			/*Återställer alla celler till 0 med actionlistener*/ 
			clearButton.setBounds(60, 450, 80, 80);
			clearButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					/**for(int i = 0; i < 9; i++){
						for(int j = 0; j < 9; j++){
							textField[i][j].setText(Integer.toString(EMPTY));

						}
					}*/
					sr.clear(); //kallar på interface ifall det behövs, vi vill dock ha null istället för 0or
					for(int i =0; i<9;  i++){
						for (int k =0; k < 9; k++){
							if(sr.getCell(i, k)==0){
								textField[i][k].setText(null);
							}else
								textField[i][k].setText(Integer.toString(sr.getCell(i, k)));
						}
					}
				}
			});

			

			/*Knapp som kallar på rekursiva lösningen med actioneventlistener*/
			solveButton.setBounds(330,450,80,80);
			solveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean shouldSolve=true;
				for(int i =0; i<9; i++){
					for(int k =0; k<9; k++){
						
						int value;
						if(textField[i][k].getText().isEmpty()){
							value=0;
							sr.setCell(i, k, value);
						}else if (allowedValue(textField[i][k].getText())){
							value = Integer.parseInt(textField[i][k].getText());
							sr.setCell(i, k, value);
						}
						else{
							JOptionPane.showMessageDialog(frame,
							"Illegal character.");
							shouldSolve=false;
							break;
						}
						
					}	
				}	
				if(sr.solve()&&shouldSolve==true){
					for(int i =0; i<9; i++){
						for(int k =0; k<9; k++){
							textField[i][k].setText(Integer.toString(sr.getCell(i, k)));
						}	
					}
				}else {
					// dialogrutan
					JOptionPane.showMessageDialog(frame,
    "Couldn't find a solution.");
					
				}
				}
			});
			frame.add(solveButton);
			frame.add(clearButton);
		}
		//for-loop stop, s�tter rutans dimensioner
		center();
		frame.setSize(500, 600);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}

	public void center() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dimension.width/2 - frame.getWidth()/2, dimension.height/2 - frame.getHeight()/2);
		frame.setSize(500, 600);
	}

	/*Privat metod som kollar att det inskrivna värdet är ok*/
	private boolean allowedValue(String text){
		if(text.matches ("1") || text.matches("2") || text.matches("3") || text.matches("4") ||
		 text.matches("5") || text.matches("6") || text.matches("7") || text.matches("8") || text.matches ("9")){
			 return true;
		 }
		 return false;

}
	
	public static void main(String[] args) {
		SudokuGUI gui = new SudokuGUI();
	}
	

}
