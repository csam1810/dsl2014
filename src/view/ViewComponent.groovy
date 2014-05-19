package view

import java.awt.LayoutManager;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel

class ViewComponent extends JPanel {
	
	public static enum LayoutEnum {
		border, grid
	}
	
	//has to be unique!!
	def String viewName
	
	def LayoutManager layout
	
	def Map<String, JComponent> viewComponents
	
	ViewComponent(String viewname) {
		viewName = viewname;
		viewComponents = [:]
	}
}
