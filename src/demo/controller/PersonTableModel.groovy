package demo.controller

import javax.swing.table.AbstractTableModel

import demo.model.Person

class PersonTableModel extends AbstractTableModel {

	def List<Person> persons
	def String[] header

	def PersonTableModel(List<Person> persons, String[] header) {

		this.header = header
		this.persons = persons
	}

	def int getRowCount() {
		return persons.size()
	}

	def int getColumnCount() {
		return header.length
	}

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

	def String getColumnName(int index) {
		return header[index]
	}

	// TODO: Add and delete
	// def void add(Person person)
	// def void delete(int rowIndex)
}
