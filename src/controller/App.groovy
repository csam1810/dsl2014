package controller

import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JTable
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener

import view.FormBuilder;
import view.View;
import model.Model


class App {
	
	private static def JFrame mainFrame = new JFrame()

	/**
	 * A mapping from unique names to views.
	 */
	private static def Map<String, View> views = [:]
	
	/**
	 * A static instance of the FormBuilder. Allows for
	 * builder-like creation of Forms.
	 */
	static def FormBuilder builder = new FormBuilder(views)
	
	/**
	 * 
	 */
	static def Model model
	
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
		 },
		 table : { String tableName ->
			 [select : { Closure closure ->
				 addSelectionListener(viewName, tableName, closure)
			 }]
		 }]
	}
	
	/**
	 * 
	 */
	private static def void addButtonListener(String viewName, String buttonName, Closure closure) {
		
		if (viewName in views && buttonName in views[viewName].viewComponents) {
			
			JButton button = views[viewName].viewComponents[buttonName]
			
			button.addActionListener(
				new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						closure(views[viewName], model)
					}
				}
			)
		}
	}
	
	private static def void addSelectionListener(String viewName, String tableName, Closure closure) {
		
		if (viewName in views && tableName in views[viewName].viewComponents) {
			
			JTable table = views[viewName].viewComponents[tableName]
			
			table.getSelectionModel().addListSelectionListener(
		        new ListSelectionListener() {
					
		            public void valueChanged(ListSelectionEvent event) {
		               closure(views[viewName], model)
		            }
		        }
			)
		}
	}
}
