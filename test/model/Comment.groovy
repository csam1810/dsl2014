package model

import model.annotations.Id;

class Comment extends Entity {
	
	@Id
	def id
	
	//@Column(length = 200)
	def user
	
	def email
	
	def webpage
	
	def datum
			
	def comments
	
	/**
	 * id = getIdFieldName(Articles)
	 * article = queryFirst(SELECT * FROM Articles WHERE ${key} = ${article}, Articles)
	 *
	 */
	//@ManyToOne('comments')
	//def Articles article
}
