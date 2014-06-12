package demo.controller

import static controller.App.*
import model.Model
import view.View
import demo.model.Person

/**
 * Controller for a view in demo application
 * contains controller logic
 */
class DetailViewController {
	static def init() {
		
		on 'DetailView' init {View view, Model model, Map args ->
			def id = args['id']
			def Person p = model.get(id, Person)
			view['LastNameText'].setText(p.lastName)
			view['FirstNameText'].setText(p.firstName)
			view['BirthdayText'].setText(p.birthday.toString())
		}
		
		on 'DetailView' button 'Back' click { View view, Model model ->			
			println "Back Button clicked!"			
			back()
		}
	}
}
