package view

import java.awt.GridLayout
import java.awt.LayoutManager
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
			GridLayout gLayout = new GridLayout(rows, cols, 1, 1)
		}]
	}
	
	static def add(String name) {
		[type : { Widgets widget ->
			[to : { viewName ->
				[onPos : { Integer xPos, Integer yPos ->
					addWidget(viewName, widget, name, xPos, yPos)
				}]
			}]
		}]
	}
	
	//not needed anymore?
//	private static def addWidget(
//		String viewName, 
//		Widgets widget, 
//		String name,
//		Integer xPos,
//		Integer yPos
//	) {
//	
//		if (viewName in views) {
//			switch (widget) {
//				case Widgets.GButton:
//					if(layoutManagers[viewName] instanceof GridLayout) {
//						GridLayout grid = (GridLayout) layoutManagers[viewName]
//						if(xPos < 0 || grid.getRows() < xPos || yPos < 0 || grid.getColumns() < yPos) {
//							//TODO here a reasonable error should be thrown
//							println("ERROR: tried to set component on impossible position! Created on default position for now.")
//							views[viewName].addButton(name)
//						} else {
//							views[viewName].addButton(name, xPos, yPos)
//						}
//					}
//			}
//		}
//	}
	
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
