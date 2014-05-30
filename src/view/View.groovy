package view

import java.awt.Component
import java.awt.GridLayout
import java.awt.LayoutManager

import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel


/**
 * Die wuerde ich View nennen.
 */
class View extends JPanel {
	
	def Map<String, JComponent> viewComponents
	//def JComponent [][] componentMatrix
	
	View(LayoutManager layoutManager) {
		
		super(layoutManager)
		viewComponents = [:]
	}

	def getAt(String componentName) {
		return viewComponents[componentName]
	}
	
	// TODO: Hier InitListener Logik hinzufuegen.
	
	//TODO AJ: not finished, commented for now
//	def void initializeComponentMatrix(GridLayout grid) {
//		int rows = grid.getRows()
//		int cols = grid.getColumns()
//		componentMatrix = new JComponent[rows][cols]
//		
//		for(i in rows-1) {
//			for(j in cols-1) {
//				JComponent dummyComponent = new JLabel("Bla");
//				//dummyComponent.setVisible(false)
//				componentMatrix[i][j] = dummyComponent
//				super.add(componentMatrix[i][j])
//			}
//		}
//	}
}
