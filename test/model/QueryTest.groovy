package model;

import static controller.App.*
import static org.junit.Assert.*
import groovy.sql.Sql

import org.junit.Before
import org.junit.Test

class QueryTest {
	
	@Before
	void setUp() {
	
		model = new Model(Sql.newInstance(
			"jdbc:mysql://localhost:3306/testDB",
			"testUser",
			"password",
			"com.mysql.jdbc.Driver"
		))
	}
	
	@Test
	public void testWhere() {
		
		def comments = from Comment where 'id = 1' end
		
		assert comments[0].id == 1
	}

}
