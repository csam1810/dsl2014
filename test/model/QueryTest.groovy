package model;

import static controller.App.*
import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class QueryTest {
	
	@Before
	void setUp() {
	
		model = new Model(
			"jdbc:mysql://localhost:3306/testDB",
			"testUser",
			"password",
			"com.mysql.jdbc.Driver"
		)
	}
	
	@Test
	public void testWhereId() {
		def comments = from Comment where 'id = 1' end	
		assert comments.size() == 1	
		assert comments[0].id == 1
	}
	
	@Test
	public void testOrder() {
		def comments = from Comment orderBy 'id DESC' end
		def ids = comments.collect { it.id }   // ids = [4,3,2,1]
		def sorted = ids.sort(false)           // sorted = [1,2,3,4]
		assert ids == sorted.reverse()
	}

}
