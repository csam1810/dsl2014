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
	
	MatrixGridPanel(int r, int c) {
		super(new GridLayout(r,c))
		componentMatrix = new JComponent[r][c]
		initMatrix()
	}
	
	def void initMatrix() {
		for(int i = 0; i < getRows(); i++) {
			for(int j = 0; i < getColumns(); j++) {
				componentMatrix[i][j] = new JButton(DUMMY_TEXT).setVisible(false)
			}
		}
	}
	
	def void addOnPos(JComponent comp, int r, int c) {
		if(r > getRows() || c > getColumns() || r < 0 || c < 0) {
			println "[MatrixGridPanel]: You chose an invalid position! Check the size of your grid!"
		} else {
			componentMatrix[r-1][c-1] = comp
			for(int i = 0; i < r; i++) {
				for(int j = 0; j < c; j++) {
					add(componentMatrix[i][j])
				}
			}
		}
	}
}
