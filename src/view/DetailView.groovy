package view

import java.awt.BorderLayout
import java.awt.GridLayout
import java.awt.LayoutManager
import javax.swing.JPanel

class DetailView extends JPanel implements ViewComponent  {
	
	public static enum LayoutEnum {
		border, grid
	}
	
	//has to be unique!!
	def String viewName
	
	def LayoutManager layout
	
	DetailView(String name, LayoutEnum layoutType) {
		viewName = name
		switch(layoutType) {
			case LayoutEnum.border:
				layout = new BorderLayout();
				break;
			case LayoutEnum.grid:
				layout = new GridLayout();
				break;
			default:
				break;
		}
	}
	
	
}
