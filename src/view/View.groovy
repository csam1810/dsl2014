package view

import javax.swing.JFrame

import model.Model
import view.ViewComponent.LayoutEnum

class View {
	
	static def JFrame mainFrame
	
	static def Map<String, ViewComponent> views
	
	static def initialize() {
		mainFrame = new JFrame()
		
		views = [:] //new HashMap<String, ViewComponent>()
	}
	
	private static def view(Model model, String selection) {
		[detail : { viewName -> 
			[layout : { LayoutEnum layout ->
				views[viewName] = new DetailView(viewName, layout)
			}]
		 },
		 lists  : {viewName -> 
			[layout : { LayoutEnum layout ->
				views[viewName] = new ListView(viewName, layout)
			}]}]
	}
	

}
