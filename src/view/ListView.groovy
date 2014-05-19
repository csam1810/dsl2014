package view

import java.awt.BorderLayout
import java.awt.GridLayout
import java.awt.LayoutManager;

import view.ViewComponent.LayoutEnum;

class ListView extends ViewComponent {
	
	ListView(String name, LayoutEnum layoutType) {
		super(name)
		switch(layoutType) {
			case LayoutEnum.border:
				layout = new BorderLayout();
				break;
			case LayoutEnum.grid:
				layout = new GridLayout();
				break;
			default:
				layout = null
				break;
		}
		
	}

}
