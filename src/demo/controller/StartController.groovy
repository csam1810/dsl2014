package demo.controller

import static controller.App.*

import javax.swing.JTable

import model.Model
import view.View
import demo.model.Person
import demo.view.*

/**
 * Controller for StartView in demo application
 * contains controller logic
 * viec jeden punkt erklären?
 */

class StartController {

	static def init() {
		
		on 'StartView' init { View view, Model model, Map args ->
			
			def JTable table = view['PersonTable']
			def header = ['ID', 'Last Name', 'First Name', 'Birthday'] as String[]
					
			table.setModel( new PersonTableModel(model[Person], header) )
		}		
		
		on 'StartView' table 'PersonTable' select { View view, Model model ->
			
			def JTable table = view['PersonTable']
			def selected = table.getSelectedRow()
			
			if (selected != -1) {
				def id = table.getValueAt(selected, 0)
				navigate('DetailView', [id: id])
			}
		}
		
		on 'StartView' button 'Add' click { View view, Model model ->
			println "Klick"
			navigate('NewView')
		}
	}
}
