package view

import java.awt.GridLayout

import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.JTextField

/**
 * Den builder koennen wir nach Herzenslust um neue Komponenten erweitern.
 */
class FormBuilder extends BuilderSupport {
	
	private def views
	private def View root
	private def Map attr
	
	FormBuilder(views) {
		this.views = views
	}

	protected void setParent(Object parent, Object child){

		if (parent != null) {
			if(parent instanceof MatrixGridPanel) {
				int row = attr['row']
				int col = attr['col']
				parent.addOnPos(child,row,col)
			} else {
				parent.add(child)
			}
			
		}
	}
	
	protected Object createNode(Object name){
		
		// TODO: Alignment-Parameter hinzufuegen.
		//       Vorsicht: gehoert dann zum unteren switch.
		switch (name) {
			case 'vbox':
				return Box.createVerticalBox()
				
			case 'hbox':
				return Box.createHorizontalBox()
		}
		return null
	}
	
	protected Object createNode(Object name, Object value){
		return null
	}
	
	protected Object createNode(Object name, Map attributes){

		switch (name) {
			case 'view':
				def id = attributes['id']
				def pad = attributes['padding'] ?: 5				
				root = new View( new GridLayout(1,1) )				
				root.setBorder(BorderFactory.createEmptyBorder(pad, pad, pad, pad))
				views[id] = root
				return root
				
			case 'grid':
				def rows = attributes['rows'] ?: 1
				def cols = attributes['cols'] ?: 1
				return new JPanel(new GridLayout(rows,cols))	
				
			case 'matrixGrid':
				def rows = attributes['rows'] ?: 1
				def cols = attributes['cols'] ?: 1
				return new MatrixGridPanel(rows,cols)

			case 'label':
				def id = attributes['id']
				def text = attributes['text']
				def label = new JLabel(text)
				root.viewComponents[id] = label
				attr = attributes
				return label
				
			case 'button':
				def id = attributes['id']
				def text = attributes['text']
				def button = new JButton(text)
				root.viewComponents[id] = button
				//AJ: save all attributes to get constraints later on
				attr = attributes
				return button
				
			case 'text':
				def id = attributes['id']
				def text = new JTextField()
				root.viewComponents[id] = text
				attr = attributes
				return text
				
			case 'table':
				def id = attributes['id']
				def table = new JTable()				
				root.viewComponents[id] = table
				attr = attributes
				return new JScrollPane(table)
		}	
		return null
	}
	
	protected Object createNode(Object name, Map attributes, Object value){
		return null
	}
}
