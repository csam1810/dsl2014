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
 * Builder handles the user defined application and creates the view components
 * 
 * 	viec bsp angeben? format?
	The following is supported:
		view: id has to be defined, gaps to the border can be defined by padding (default value is 5), 
		it generates  a view which is a Gridlayout with 1 Cell which is added to list views
	    return View which is a JPanel
				
		grid: size is defined by number of rows and columns 
		returns a JPanel
				
		matrixGrid: size is defined by number of rows and columns, default value is first cell with row and column 1
		return MatrixGridPanel
		viec default values

		label: id and text has to be defined, data are stored as attributes map
		return JLabel
				
		button: id and text has to be defined, data are stored as attributes map
		return JButton
								
		text: id has to be defined, data are stored as attributes map
		return new JTextField
				
		table: id has to be defined, data are stored as attributes map
		return JTable				
	
 */
class FormBuilder extends BuilderSupport {
	
	private def views
	private def View root
	private def Map attr
	
	FormBuilder(views) {
		this.views = views
	}

	/**
	 * Add child object to parent
	 * If parent is an instance of MatrixGridPanel, the child is add at the given position, 
	 * otherwise just added to parent
	 */
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
	
	/**
	 * viec 
	 */
	protected Object createNode(Object name){
		
		// viec - was ist damit gemeint? TODO: Alignment-Parameter hinzufuegen.
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
	
/**
 * see FormBuilder
 */
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
				//save all attributes to get constraints later on
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
