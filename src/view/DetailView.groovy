package view

import java.awt.BorderLayout
import java.awt.GridLayout
import java.awt.LayoutManager
import javax.swing.JComponent
import javax.swing.JPanel
import view.ViewComponent.LayoutEnum

class DetailView extends ViewComponent  {
	
	DetailView(String name, LayoutEnum layoutType) {
		super(name, layoutType)
	}	
}
