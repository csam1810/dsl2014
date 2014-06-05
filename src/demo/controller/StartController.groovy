package demo.controller

import static controller.App.*

import javax.swing.JTable

import model.Model
import view.View
import demo.model.Person
import demo.view.*

class StartController {

	static def init() {
		
		on 'StartView' init { View view, Model model ->
			def JTable table = view['PersonTable']
			def header = ['Last Name', 'First Name', 'Birthday'] as String[]
					
			table.setModel( new PersonTableModel(model[Person], header))
		}
		
		on 'StartView' button 'LoadButton' click { View view, Model model ->
			
			
//			def JTable table = view['PersonTable']			
//			def header = ['Last Name', 'First Name', 'Birthday'] as String[]
//					
//			table.setModel( new PersonTableModel(model[Person], header) )
			
			println "Load Button clicked!"
		}
		
		on 'StartView' button 'ExitButton' click { View view, Model model ->
			
			println "Exit Button clicked!"
			navigate "DetailView"
		}
		
		on 'StartView' table 'PersonTable' select { View view, Model model ->
			
			def JTable table = view['PersonTable']
			
			// TODO: Zu anderer View navigieren mit parametern.
			// navigate('ViewName', [id: table.getSelectedRow()])
			// navigate('ViewName')
			println table.getSelectedRow() + " selected."
		}
	}
}
