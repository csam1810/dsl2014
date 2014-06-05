package demo.model

import model.Entity
import model.annotations.Id

class Person extends Entity {

	@Id
	def id
	
	def lastName
	
	def firstName
	
	def birthday
}
