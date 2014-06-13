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
class NewController {
	
	static def init() {
		
		on 'NewView' button 'Back' click { View view, Model model ->	
								
			back()
		}
		
		on 'NewView' button 'Save' click { View view, Model model ->
			
			Person p = new Person()
			
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