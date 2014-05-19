package model.annotations

import java.lang.annotation.Annotation

import model.Entity

/**
 * A collection of static methods to query and process the database 
 * annotations.
 * 
 * @author alexandra
 * @author carmen
 * @author mathias
 */
class AnnotationUtils {

	/**
	 * Queries all defined fields in an entity for the field with the '@Id'
	 * annotation flag present and returns the field's name.
	 *
	 * @param clazz  A database entity class type.
	 * @return  The name of the id field.
	 */
	static def getIdFieldName(Class clazz) {
		
		def fields = clazz.declaredFields
		def field = fields.find { f -> f.getAnnotation(Id) != null }
		field?.name
	}
	
	/**
	 * Queries all defined fields in an entity for the field with the '@Id'
	 * annotation flag present and returns the field's value.
	 *
	 * @param entity  A database entity.
	 * @return  The value of the id field.
	 */
	static def getIdFieldValue(Entity entity) {
		entity?."${getIdFieldName(entity.class)}"
	}
	
	/**
	 * 
	 * 
	 * @param clazz  The database entity class type.
	 * @return  A mapping from field names to annotations for that field.
	 */
	@Deprecated
	static def getFieldAnnotations(Class clazz) {
		
		def Map<String, Annotation[]> map = [:]
		
		clazz.declaredFields.each { field ->
			map[field.name] = field.annotations
		}
		map
	}
}
