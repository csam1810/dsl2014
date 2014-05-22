package model

import static model.annotations.AnnotationUtils.*

import model.annotations.Id
import org.junit.Test

class AnnotationUtilsTest {

	class Minimal extends Entity {
		
		@Id
		def myId = 1
	}
	
	Minimal m = new Minimal()
	
	@Test
	void testGetIdFieldName() {		
		assert getIdFieldName(Minimal) == 'myId'
	}
	
	@Test
	void testGetIdFieldNameInstance() {
		assert getIdFieldName(m.class) == 'myId'
	}
	
	@Test
	void testGetIdFieldNameNull() {
		assert getIdFieldName(null) == null
	}
	
	@Test
	void testGetIdFieldValue() {
		assert getIdFieldValue(m) == 1
	}
	
	@Test 
	void testGetIdFieldValueNull() {
		assert getIdFieldValue(null) == null
	}
}
