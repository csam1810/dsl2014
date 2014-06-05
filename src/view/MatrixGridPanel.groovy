package view

import java.awt.GridLayout
import javax.swing.*

/**
 * Basic class that can be used for a matrix implementation of the grid layout.
 * 
 * @author alexandra
 *
 */
class MatrixGridPanel extends View{
	def static String DUMMY_TEXT = "DummyComponent"
	def JComponent [][] componentMatrix;
	def int rows
	def int cols
	
	MatrixGridPanel(int r, int c) {
		super(new GridLayout(r,c))
		rows = r
		cols = c
		componentMatrix = new JComponent[r][c]
		initMatrix()
	}
	
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
	
	def void addOnPos(JComponent comp, int r, int c) {
		if(r > rows || c > cols || r < 0 || c < 0) {
			println "[MatrixGridPanel]: You chose an invalid position! Check the size of your grid!"
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
