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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SudokuGUI extends JPanel {
	public JFrame frame = new JFrame("Sudoku Solver");
	public JTextField textField[][];
	public JButton jbutton;
	
	public SudokuGUI() {
		textField = new JTextField[9][9];
		setGUI();
		
	}
	
	
	public void solve() {
		
	}
	public void setGUI() {
		
		Random rand = new Random();
		
		Color[] col = new Color[9];
		
		for(int i = 0; i <9; i++) {
			int randColNbr = rand.nextInt(255);
			Color randCol = Color.getHSBColor(randColNbr, randColNbr, randColNbr);
			col[i] = randCol;
		}
		
		
		
		
		frame = new JFrame("SUDOKU SOLVER");
		
		
		//l�gger in rutorna och fixar designen f�r dem
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				textField[i][j] = new JTextField();
				textField[i][j].setBounds(50+j*40, 50+i*40, 30, 30);
				textField[i][j].setBackground(col[(i/3)*3+(j/3)]);
				Font font1 = new Font("SansSerif", Font.BOLD, 20);
				textField[i][j].setFont(font1);
				textField[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				
				frame.add(textField[i][j]);
			}
			
			/* L�gger till solveknappen som kallar p� solve-metoden via
			 * en actionlistener */
			jbutton = new JButton ("SOLVE");
			jbutton.setBounds(200,450,80,80);
			jbutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				solve();	
				}
			});
			frame.add(jbutton);
		}
		//for-loop stop, s�tter rutans dimensioner
		center();
		frame.setSize(500, 600);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	public int get_element(int i, int j) {
		String txt = textField[i][j].getText();
		if(txt.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(txt.trim());
	}
	
	public void set_element(int i, int j, int val) {
		textField[i][j].setText(String.valueOf(val));
	}
	
	public void clear() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j<9; j++) {
				textField[i][j].setText("");
			}
		}
	}
	public void center() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dimension.width/2 - frame.getWidth()/2, dimension.height/2 - frame.getHeight()/2);
		frame.setSize(500, 600);
	}
	
	public static void main(String[] args) {
		SudokuGUI gui = new SudokuGUI();
	}
	

}
