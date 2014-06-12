package view

import java.awt.GridLayout
import javax.swing.*

/**
 * MatrixGridLayout provide opportunity to define a grid and add components in defined cell 
 */
class MatrixGridPanel extends View{
	def static String DUMMY_TEXT = "DummyComponent"
	def JComponent [][] componentMatrix;
	def int rows
	def int cols
	
	/**
	 * Constructor for MatrixGridPanel with a defined size 
	 * @param r Number of rows
	 * @param c Number of columns
	 */
	MatrixGridPanel(int r, int c) {
		super(new GridLayout(r,c))
		rows = r
		cols = c
		componentMatrix = new JComponent[r][c]
		initMatrix()
	}
	
	/**
	 * All cells in the matrix are initially filled with dummy elements
	 */
	def void initMatrix() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				JButton tmp = new JButton(DUMMY_TEXT+i+j)
				tmp.setVisible(false)
				componentMatrix[i][j] = tmp
				add(componentMatrix[i][j])
			}
		}
	}
	
	/**
	 *viec mögliche componenten angeben? 
	 * Add a JComponent at the position defined by row and column
	 * @param comp JComponent which should be added
	 * @param r row 
	 * @param c column
	 */
	def void addOnPos(JComponent comp, int r, int c) {
		if(r > rows || c > cols || r < 0 || c < 0) {
			println "[MatrixGridPanel]: You choose an invalid position! Check the size of your grid!"
		} else {
			removeAll();
			componentMatrix[r][c] = comp
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < cols; j++) {
					add(componentMatrix[i][j])
				}
			}
			revalidate();
			repaint();
		}
	}
}
