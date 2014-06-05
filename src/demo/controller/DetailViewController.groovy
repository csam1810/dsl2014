package demo.controller

import model.Model
import view.View
import static controller.App.*

class DetailViewController {
	static def init() {
		
		on 'DetailView' init {View view, Model model, Map args ->
			println args['id']
		}
		
		on 'DetailView' button 'Back' click { View view, Model model ->
			
			println "Back Button clicked!"
			
			back()
		}
	}
}
