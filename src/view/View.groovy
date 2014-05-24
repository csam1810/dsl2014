package view

import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import javax.swing.JButton
import javax.swing.JFrame

import view.ViewComponent.LayoutEnum

class View {
	
	static enum Widgets {
		GButton,
		TextBox
	}
	
	private static def JFrame mainFrame = new JFrame()
	
	static def Map<String, ViewComponent> views = [:]
	
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
			views[viewName] = new ViewComponent(new GridLayout(rows, cols, 1, 1))
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
			views[viewName].viewComponents[buttonName].addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closure()
					}
				}
			)
		}
	}
	
}
