package view

import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel

class View {
	
	static enum Widgets {
		GButton,
		TextBox
	}
	
	private static def JFrame mainFrame = new JFrame()
	
	private static def Map<String, ViewComponent> views = [:]
	
	/**
	 * A static instance of the FormBuilder. Allows for
	 * builder-like creation of Forms.
	 */
	static def FormBuilder builder = new FormBuilder(views)
	
//	static def initialize() {
//		mainFrame = new JFrame()		
//		views = [:] //new HashMap<String, ViewComponent>()
//	}
	
	static def init(String startPanel) {
		
		if (views.containsKey(startPanel)) {
			mainFrame.setContentPane(views[startPanel])
			mainFrame.pack()
			mainFrame.setVisible(true)
		}
	}
	
//	static def view() {
//		[detail : { viewName -> 
//			[layout : { LayoutEnum layout ->
//				views[viewName] = new DetailView(viewName, layout)
//			}]
//		 },
//		 lists  : {viewName -> 
//			[layout : { LayoutEnum layout ->
//				views[viewName] = new ListView(viewName, layout)
//			}]
//		 }]
//	}
	
	static def view(String viewName) {
		[grid : { Integer rows, Integer cols ->
			views[viewName] = new ViewComponent(new GridLayout(rows, cols, 3, 3))
		}]
	}
	
	static def add(String name) {
		[type : { Widgets widget ->
			[to : { viewName ->
				addWidget(viewName, widget, name)
			}]
		}]
	}
	
	private static def addWidget(
		String viewName, 
		Widgets widget, 
		String name
	) {
	
		if (viewName in views) {
			switch (widget) {
				case Widgets.GButton:
					views[viewName].addButton(name)
			}
		}
	}
	
	static def on(String viewName) {
		[button : { String buttonName ->
			[click : { Closure closure ->
				addButtonListener(viewName, buttonName, closure)
			}]
		}]
	}
	
	private static def addButtonListener(
		String viewName, 
		String buttonName, 
		Closure closure
	) {
		
		if (viewName in views && buttonName in views[viewName].viewComponents) {
			views[viewName][buttonName].addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closure(views[viewName])
					}
				}
			)
		}
	}
}
