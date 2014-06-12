package demo.model

import model.Entity
import model.annotations.Id

/**
 * Defined table and the fields for demo application
 * @Id marks the database key 
 */
class Person extends Entity {

	@Id
	def id
	
	def lastName
	
	def firstName
	
	def birthday
}
