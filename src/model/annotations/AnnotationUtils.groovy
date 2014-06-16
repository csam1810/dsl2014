package model.annotations

import java.lang.annotation.Annotation

import model.Entity

/**
 * A collection of static methods to query and process the database 
 * annotations.
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
		
		def fields = clazz?.declaredFields
		def field = fields?.find { f -> f.getAnnotation(Id) != null }
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
		entity?."${getIdFieldName(entity?.class)}"
	}
}
