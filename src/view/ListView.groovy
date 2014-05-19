package view

import java.awt.BorderLayout
import java.awt.GridLayout
import java.awt.LayoutManager;

import view.DetailView.LayoutEnum;

class ListView {
	
	public static enum LayoutEnum {
		border, grid
	}
	
	//has to be unique!!
	def String viewName
	
	def LayoutManager layout
	
	ListView(String name, LayoutEnum layoutType) {
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
