package view

import java.awt.Component
import java.awt.GridLayout
import java.awt.LayoutManager

import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel

class ViewComponent extends JPanel {
	
	public static enum LayoutEnum {
		border, grid
	}
	
	//has to be unique!!
	def String viewName
	
	//def LayoutManager layout
	
	def Map<String, JComponent> viewComponents
	def JComponent [][] componentMatrix
	
	ViewComponent(LayoutManager layoutManager) {
		super(layoutManager)
		viewComponents = [:]
		if(layoutManager instanceof GridLayout) {
			//TODO AJ: not finished, commented for now
			//initializeComponentMatrix((GridLayout) layoutManager)
		}
	}
	
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
	
//	ViewComponent(String viewname, LayoutEnum layoutType) {
//		viewName = viewname;
//		
//		switch(layoutType) {
//			case LayoutEnum.border:
//				layout = new BorderLayout();
//				break;
//			case LayoutEnum.grid:
//				layout = new GridLayout();
//				break;
//			default:
//				layout = null
//				break;
//		}
//		
//		viewComponents = [:]
//	}
	
	/**
	 * Adds a new button to this view.
	 * 
	 * @param name  The name of the button.
	 */
	def void addButton(String name) {
		// Hier waere Platz um den button zu stylen.
		this.addWithName(name, new JButton(name))
	}
	
	/**
	 * Adds a new button to this view on the specified position.
	 *
	 * @param name  The name of the button.
	 */
	def void addButton(String name, int xPos, int yPos) {
		// Hier waere Platz um den button zu stylen.
		this.addWithName(name, new JButton(name))
	}
	
	/**
	 * Adds a component to this view and a map for later
	 * retrieval by its name.
	 * 
	 * @param name  The name of the component in the map.
	 * @param component  The component.
	 */
	private def void addWithName(String name, Component component) {
		
		super.add(component)
		this.viewComponents[name] = component
	}
}
