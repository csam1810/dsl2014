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
	
	private static def Stack<String> viewStack = new Stack<String>()
	
	private static def Map<String, View> views = [:]
	
	/**
	 * A static instance of the FormBuilder. Allows for
	 * builder-like creation of Forms.
	 */
	static def FormBuilder builder = new FormBuilder(views)
	
	/**
	 * A static instance of the data model. Needs to be initialized
	 * by the user like this: <br><br>
	 * <code>
	 * model = new Model(Sql.newInstance(        <br>
	 * 		"jdbc:mysql://localhost:3306/demoDB", <br>
	 * 		"demoUser",                           <br>
	 * 		"password",                           <br>
	 * 		"com.mysql.jdbc.Driver"               <br>
	 * ))
	 * </code>
	 */
	static def Model model
	
	// TODO: Navigator-Logik
	/**
	 * Initializes the start view of the application.
	 * 
	 * @param startPanel  The unique name of the start view.
	 */
	static def void navigate(String panelName) {
		navigate(panelName,[:])
	}
	
	static def void navigate(String panelName, Map args) {
		if (panelName in views) {
			viewStack.push(panelName)
			printStack("navigate to ${panelName}")
			displayPanel(panelName, args)
		}
	}
	
	static def void back() {
		back([:])
	}
	
	static def void back(Map args) {
		if(!viewStack.isEmpty()) {
			viewStack.pop();
			printStack("back to ${viewStack.peek()}")
			displayPanel(viewStack.peek(),args)
		} else {
			println "[INFO]: trying to pop empty stack in back()."
		}
	}
	
	static def void printStack(String action) {
		println action
		println "--------------------------------"
		for (String vn in viewStack) {
			println " - ${vn}"
		}
		println "\n"
	} 
	
	private static def void displayPanel(String panelName, Map args) {
		mainFrame.setContentPane(views[panelName])
		mainFrame.pack()
		mainFrame.setVisible(true)
		//call init listeners
		views[panelName].callInitListeners(model,args)
	}
	
	/**
	 * DSL to query the database.
	 *
	 * @see Model#query(String, Class)
	 *
	 * @return  DB entries that match the query.
	 */
	static def from(Class clazz) {
		
		def query = new StringBuilder()
		query << "SELECT * FROM ${clazz.simpleName}"
		[where   : { String cond -> where(query, clazz, cond) },
		 orderBy : { String ord  -> order(query, clazz, ord)  },
		 end     : model.query(query.toString(), clazz)]
	}

	private static def where(StringBuilder query, Class clazz, String cond) {
		
		query << " WHERE ${cond}"
		[orderBy : { String ord  -> order(query, clazz, ord) },
		 end     : model.query(query.toString(), clazz)]
	}
	
	private static def order(StringBuilder query, Class clazz, String ord) {
		
		query << " ORDER BY ${ord}"
		[end : model.query(query.toString(), clazz)]
	}
	
	/**
	 * Object literal DSL to specify controller actions.
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
		 },
		 init : { Closure closure ->
			 addInitListener(viewName, closure)
		 }]
	}
	
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
						if (!event.getValueIsAdjusting()) {
							closure(views[viewName], model)
						}
		            }
		        }
			)
		}
	}
	
	private static def void addInitListener(String viewName, Closure closure) {
		
		if (viewName in views) {
			views[viewName].addInitListener(closure)
		}
	}
}
