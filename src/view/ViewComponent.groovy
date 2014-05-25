package view

import java.awt.Component
import java.awt.LayoutManager

import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel

class ViewComponent extends JPanel {
	
	public static enum LayoutEnum {
		border, grid
	}
	
	//has to be unique!!
	def String viewName
	
	//def LayoutManager layout
	
	def Map<String, JComponent> viewComponents
	
	ViewComponent(LayoutManager layoutManager) {
		
		super(layoutManager)
		viewComponents = [:]
	}

	//ViewComponent() {
	//	
	//	super()
	//	viewComponents = [:]
	//}
		
	/**
	 * view["SaveButton"]
	 * 
	 * @param componentName
	 * @return
	 */
	def getAt(String componentName) {
		return viewComponents[componentName]
	}
	
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
		// Hier waere Platz um den button uniform zu stylen.
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
