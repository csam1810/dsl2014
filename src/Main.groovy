import static model.Query.*
import static model.annotations.AnnotationUtils.*
import model.Entity
import model.Model
import model.annotations.Column
import model.annotations.Id

/** 
 * Install JDBC driver.
 * 
 * sudo apt-get install libmysql-java
 * 
 * Add jar to project.
 * 
 * /usr/share/java/mysql-connector-java.jar
 * 
 */

//def builder = new ConnectionBuilder() 
//
//builder {
//	host:     'localhost'
//	database: 'feedback'
//	user:     'sqluser'
//	password: 'sqluserpw'
//}
//
//println builder.instance.url()

class Articles extends Entity {
	
	@Id
	def id
	
	def text = "Longish article text."
	
	/**
	 * Query article.
	 * Get all that are annotated with e.g. @ManyToOne.
	 * id = getIdFieldValue(article)
	 * key = getOneToManyValue(article.comments)
	 * comments = query(SELECT * FROM Comments WHERE ${key} = ${id}, Comments)
	 * For all comments:
	 * 		comments."${key}" = article
	 */
	//@OneToMany('article')
	def List<Comments> comments
}

/**
 * Attributes defined with def will become private 
 * fields with Getters and Setters.
 * 
 * TODO: Check Types.
 * TODO: Create SQL file.
 * TODO: Local AST Transformation for @Entity
 */
class Comments extends Entity {
	
	@Id
	def id       = 1
	
	@Column(length = 200)
	def myuser   = 'horst'
	
	def email    = 'that@that.com'
	
	def webpage  = 'www.example.com'
	
	def datum    = new Date()
	
	def summary  = 'Some Text.'
	
	def comments = 'My second comment.'
	
	/**
	 * id = getIdFieldName(Articles)
	 * article = queryFirst(SELECT * FROM Articles WHERE ${key} = ${article}, Articles)
	 * 
	 */
	//@ManyToOne('comments')
	//def Articles article 
}

def model = new Model()

c = new Comments()

// Persist c to model
//model << c

// Delete c from model
//model >> c

// Get 
//def Comments row = get 2 from Comments on model
//println row.myuser

// Get all Comments
//println model[Comments]

// Alll Query things

// <model> evtl nicht noetig wenn model static ist.

//on <model> select <sel> from <clazz>
//on <model> select <sel> from <clazz> order <ord>
//on <model> select <sel> from <clazz> where <whr>
//on <model> select <sel> from <clazz> where <whr> order <ord>

//save <entity> to <model>
//persist <entity> to <model>
//update <entity> on <model>
//delete <entity> on <model>
//get <id> from <clazz> on <model>