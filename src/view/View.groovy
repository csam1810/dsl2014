package view

import java.awt.Component
import java.awt.GridLayout
import java.awt.LayoutManager

import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import model.Model

/**
 * Definition of view with components of the view and listener 
 *
 */
class View extends JPanel {
	
	private def List<Closure> initListeners
	
	def Map<String, JComponent> viewComponents
	//def JComponent [][] componentMatrix //viec check, löschen?
	
	/**
	 * View constructor.
	 * For every view a layoutManager has to be defined:  
	 * Either gridLayout or matrixGridLayout
	 * @param layoutManager LayoutManager for view
	 * @link java.awt.GridLayout
	 * @see MatrixGridPanel
	 */
	View(LayoutManager layoutManager) {
		
		super(layoutManager)
		viewComponents = [:]
		initListeners = []
	}
	
	/**
	 * Call all previously defined init listener for view
	 * @param model Used model is necessary
	 * @param args Arguments can be defined
	 */
	public def void callInitListeners(Model model, Map args) {
		for(Closure listener in initListeners) {
			listener(this, model, args)
		}
	}
	
	/**
	 * Listener are added to list
	 * @param listener which will be added to initListener of view
	 */
	public def void addInitListener(Closure listener) {
		initListeners.add(listener)
	}

	/**
	 * Get view component object by name
	 * @param componentName Name of requested component
	 * @return requested component
	 */
	def getAt(String componentName) {
		return viewComponents[componentName]
	}

}
