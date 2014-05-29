package view

import java.awt.GridLayout
import java.awt.LayoutManager
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel


/**
 * Diese Klasse wuerde ich gerne umbenennen zu App weil sie spaeter
 * alle statischen Elemente (views, model, usw.) vereint.
 */
class View {
	
	private static def JFrame mainFrame = new JFrame()

	/**
	 * A mapping from unique names to views.
	 */
	private static def Map<String, ViewComponent> views = [:]
	
	/**
	 * A static instance of the FormBuilder. Allows for
	 * builder-like creation of Forms.
	 */
	static def FormBuilder builder = new FormBuilder(views)
	
	// Kommt spaeter noch dazu.
	//static def Model model
	
	// Hier kommt spaeter noch ein Navigator objekt dazu.
	// Mit diesem Objekt soll man gemuetlich in der History
	// zurueckblaettern koennen und natuerlich auch einfach
	// beliebig navigieren.
	/**
	 * Initializes the start view of the application.
	 * 
	 * @param startPanel  The unique name of the start view.
	 */
	static def void init(String startPanel) {
		
		if (startPanel in views) {
			mainFrame.setContentPane(views[startPanel])
			mainFrame.pack()
			mainFrame.setVisible(true)
		}
	}
	
	/**
	 * Object literal DSL to specifiy controller actions.
	 * 
	 * @param viewName  The unique name of the view where the control element
	 *                  is positioned.
	 */
	static def on(String viewName) {
		[button : { String buttonName ->
			[click : { Closure closure ->
				addButtonListener(viewName, buttonName, closure)
			}]
		}]
	}
	
	/**
	 * 
	 */
	private static def void addButtonListener(String viewName, String buttonName, Closure closure) {
		
		if (viewName in views && buttonName in views[viewName].viewComponents) {
			views[viewName].viewComponents[buttonName].addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Der Closure werden wir spaeter noch das Model uebergeben.
						closure(views[viewName])
					}
				}
			)
		}
	}
}
