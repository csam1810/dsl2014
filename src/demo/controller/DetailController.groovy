package demo.controller

import static controller.App.*

import java.text.DateFormat

import model.Model
import view.View
import demo.model.Person

/**
 * Controller for a view in demo application
 * contains controller logic
 */
class DetailController {
	
	static def id
	static def Person p
	
	static def init() {
		
		on 'DetailView' init {View view, Model model, Map args ->
			
			// Assign id and person to some globally visible static variables.
			id = args['id']
			p = model.get(id, Person)
			
			// Initialize the view with the persons values.
			view['LastNameText'].setText(p.lastName)
			view['FirstNameText'].setText(p.firstName)
			view['BirthdayText'].setText(p.birthday.toString())
		}
		
		on 'DetailView' button 'Back' click { View view, Model model ->	
								
			back()
		}
		
		on 'DetailView' button 'Save' click { View view, Model model ->
			
			// Set new values.
			p.lastName = view['LastNameText'].getText()
			p.firstName = view['FirstNameText'].getText()
			p.birthday = view['BirthdayText'].getText()
			
			// Update the person in the database.
			model << p
			
			back()
		}
	}
}
