package view

import java.awt.GridLayout

import javax.swing.JButton
import javax.swing.JPanel

class FormBuilder extends BuilderSupport {
	
	private def views
	private def ViewComponent root
	private def JPanel current
	
	FormBuilder(views) {
		this.views = views
	}

	protected void setParent(Object parent, Object child){
	}
	
	protected Object createNode(Object name){
		return null
	}
	
	protected Object createNode(Object name, Object value){
		return null
	}
	
	protected Object createNode(Object name, Map attributes){

		switch (name) {
			case 'view':
				def id = attributes['id']
				root = new ViewComponent(new GridLayout(1,1))
				current = root
				views[id] = root
				break
			case 'grid':
				def rows = attributes['rows'] ?: 1
				def cols = attributes['cols'] ?: 1
				def panel = new JPanel(new GridLayout(rows, cols))
				current.add(panel)
				current = panel
				break
			case 'button':
				def id = attributes['id']
				def text = attributes['text']
				def button = new JButton(text)
				root.viewComponents[id] = button
				current.add(button)
				break
		}	
		return null
	}
	
	protected Object createNode(Object name, Map attributes, Object value){
		return null
	}
	
	protected void nodeCompleted(Object parent, Object node) {
	}
}
