package model

import groovy.sql.Sql
import model.annotations.Column
import model.annotations.Id

import org.junit.After
import org.junit.Before
import org.junit.Test

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException

class ModelTest {

	def Model model
	
	def Comment c
	def Comment d
	def Comment e
	def Comment f
	
//	class Article extends Entity {
//		
//		@Id
//		def id
//		
//		def user
//		
//		def title
//		
//		def datum
//		
//		def text
//		
//		/**
//		 * Query article.
//		 * Get all that are annotated with e.g. @ManyToOne.
//		 * id = getIdFieldValue(article)
//		 * key = getOneToManyValue(article.comments)
//		 * comments = query(SELECT * FROM Comments WHERE ${key} = ${id}, Comments)
//		 * For all comments:
//		 * 		comments."${key}" = article
//		 */
//		//@OneToMany('article')
//		//def List<Comments> comments
//	}
	

	
	@Before 
	void setUp() {
	
		this.model = new Model(
			"jdbc:mysql://localhost:3306/testDB",
			"testUser",
			"password",
			"com.mysql.jdbc.Driver"
		)	
		
		this.c = new Comment()
		this.c.user = 'harald'
		this.c.webpage = 'www.harald.com'
		this.c.email = 'harald@gmail.com'
		this.c.datum = new Date()
		this.c.comments = 'Harald comment.'	
		
		this.d = new Comment()
		this.d.user = 'hermann'
		this.d.webpage = 'www.hermann.com'
		this.d.email = 'hermann@gmail.com'
		this.d.datum = new Date()
		this.d.comments = 'Hermann comment.'
		
		this.e = new Comment()
		this.e.user = 'tom'
		this.e.webpage = 'www.tom.com'
		this.e.email = 'tom@gmail.com'
		this.e.datum = new Date()
		this.e.comments = 'Tom comment.'

		this.f = new Comment()
		this.f.user = 'heinz'
		this.f.webpage = 'www.heinz.com'
		this.f.email = 'heinz@gmail.com'
		this.f.datum = new Date()
		this.f.comments = 'Heinz comment.'
	}
	
/* ****************************************************************************
 * <T extends Entity> T get(Integer id, Class<T> clazz)                       *
 **************************************************************************** */
	@Test 
	void testGet() {
		
		Comment c = model.get(1, Comment)
		assert c.user == 'lars'
		
		c = model.get(4, Comment)
		assert c.user == 'crow'
	}
	
	// exprected=SqlException
	@Test 
	void testGetOutOfRange() {		
		assert model.get(0, Comment) == null
	}
	
	@Test(expected = MySQLSyntaxErrorException)
	void testGetNull() {
		model.get(1, null)
	}

/* ****************************************************************************
 * Integer save(Entity entity)                                                *
 **************************************************************************** */
	@Test 
	void testSave() {
		
		// Saving harald.
		this.c.id = model.save(this.c)
		Comment cn = model.get(this.c.id, Comment)
		
		assert this.c.id == cn.id
	}
	
	// expected=SqlException
	@Test(expected = MySQLIntegrityConstraintViolationException)
	void testSaveAlreadySaved() {
		 
		this.d.id = model.save(this.d)
		// Saving the same Comment (this Time with id set) 
		// twice causes an SqlException.
		model.save(this.d)
	}

/* ****************************************************************************
 * Integer persist(Entity entity)                                             *
 **************************************************************************** */
	@Test
	void testPersist() {
		
		this.e.id = model.persist(this.e)
		// Should not be null anymore.
		Comment e1 = model.get(this.e.id, Comment)
		
		// Persisting the same Comment (with Id field set)
		// twice should work fine.
		model.persist(this.e)
		// Should be the same as e1.
		Comment e2 = model.get(this.e.id, Comment)
		
		assert this.e.id != null
		assert e1 != null
		assert e2 != null
		assert e1.id == e2.id
	}

/* ****************************************************************************
 * Integer delete(Entity entity)                                              *
 **************************************************************************** */
	@Test
	void testDelete() {
		
		this.f.id = model.save(this.f)
		model.delete(this.f)
	}
	
	@After
	void tearDown() {
		
		model.delete(this.c)
		model.delete(this.d)
		model.delete(this.e)
	}
}
