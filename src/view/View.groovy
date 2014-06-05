package view

import java.awt.Component
import java.awt.GridLayout
import java.awt.LayoutManager

import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import model.Model


class View extends JPanel {
	
	private List<Closure> initListeners
	
	def Map<String, JComponent> viewComponents
	//def JComponent [][] componentMatrix
	
	View(LayoutManager layoutManager) {
		
		super(layoutManager)
		viewComponents = [:]
		initListeners = []
	}
	
	public def void callInitListeners(Model model) {
		for(Closure listener in initListeners) {
			listener(this, model)
		}
	}
	
	public def void addInitListener(Closure listener) {
		initListeners.add(listener)
	}

	def getAt(String componentName) {
		return viewComponents[componentName]
	}

}
