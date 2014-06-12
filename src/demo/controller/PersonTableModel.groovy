package demo.controller

import javax.swing.table.AbstractTableModel

import demo.model.Person

/**
 * Defined Table for demo application
 */
class PersonTableModel extends AbstractTableModel {

	def List<Person> persons
	def String[] header

	def PersonTableModel(List<Person> persons, String[] header) {

		this.header = header
		this.persons = persons
	}
	
	
	/**
	 * Get number of datasets
	 * @return Number of persons
	 * */
	def int getRowCount() {
		return persons.size()
	}

	/**
	 * Get number of fields
	 * @return Number of fields
	 */
	def int getColumnCount() {
		return header.length
	}

	/**
	 * Get data for a specific dataset with given row and the field depending on the given column
	 */
	def Object getValueAt(int rowIndex, int columnIndex) {
		
		def Person p = this.persons.get(rowIndex)
		
		switch (columnIndex) {
			case 0:
				return p.id
			case 1:
				return p.lastName
			case 2:
				return p.firstName
			case 3:
				return p.birthday.toString()
			default:
				return 'xxx'
		}
	}

	/**
	 * Get name of field for a field with given index
	 * @return fieldname
	 */
	def String getColumnName(int index) {
		return header[index]
	}

	// viec TODO: Add and delete
	// def void add(Person person)
	// def void delete(int rowIndex)
}
