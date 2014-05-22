package model

import static model.annotations.AnnotationUtils.*
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import java.lang.reflect.Field

/**
 * TODO: ConnectionBuilder Support?
 *
 * @author alexandra
 * @author carmen
 * @author mathias
 */
class Model {

	/** Connection to MySQl database. */
	private def Sql connection

	/**
	 * 
	 * 
	 */
	Model(Sql connection) {
		this.connection = connection
	}
	
	/**
	 * Persists the entity in the model. If the entity has its ID set to
	 * {@code null}, it will be saved to the model and assigned an ID by 
	 * the database. If the specified entity's ID already exists, the 
	 * appropriate database row will be updated. <p>
	 * This function returns the ID of the inserted or updated entity. 
	 * 
	 * @param entity  The entity to be persisted.
	 * @return  The assigned ID.
	 */
	def leftShift(Entity entity) {
		persist(entity)
	}
	
	/**
	 * Deletes the entity from the model.
	 * 
	 * @param entity  The entity to be deleted.
	 * @return  Nothing.
	 */
	def rightShift(Entity entity) {
		delete(entity)
	}
	
	/**
	 * Returns the entire table for a given class.
	 *
	 * @param clazz  The class.
	 * @return  A Collection of all table entries for a given class.
	 */
	def getAt(Class clazz) {
		query("SELECT * FROM " + clazz?.simpleName, clazz)
	}
	
	/**
	 * 
	 * 
	 * @param sql
	 * @param clazz
	 * @return
	 */
	def <T extends Entity> List<T> query(String sql, Class<T> clazz) {
		return connection.rows(sql).collect { row -> asClass(row, clazz) }
	}

	/**
	 * 
	 * 
	 * @param sql
	 * @param clazz
	 * @return
	 */
	def <T extends Entity> T queryFirst(String sql, Class<T> clazz) {
		return { r -> (r.empty) ? null : r?.first() }( query(sql, clazz) )
	}

	/**
	 * 
	 * 
	 * @param id
	 * @param clazz
	 * @return
	 */
	def <T extends Entity> T get(Integer id, Class<T> clazz) {

		// TODO: Null Check.
		def name = getIdFieldName(clazz)
		return queryFirst(
			"SELECT * FROM " + clazz?.simpleName + 
			" WHERE " + name + " = ${id}",
			clazz
		)
	}

	/**
	 * 
	 * 
	 * @param entity
	 * @return
	 */
	def Integer save(Entity entity) {
		
		def props = getProperties(entity)
		def columns = []
		def qmarks = []
		def values = []
		
		// Save keys, question marks and values to different lists.
		props.each { key, value ->
			columns << key
			qmarks << "?" 
			values << value
		}	
		
		// Concatenate column names and question marks.
		def colStr = columns.join(', ')
		def valStr = qmarks.join(', ')
		
		// Insert data and retrieve assigend id.
		def id = connection.executeInsert(
			"INSERT INTO " + entity?.class.simpleName + 
			" (" + colStr + ") VALUES (" + valStr + ")", 
			values
		)
		
		// id is List<List<Integer>> in our case. 
		// Get rid of the extra lists.
		id?.first()?.first()
	}
	//connection.dataSet(entity.class).add( getProperties(entity) )
	
	/**
	 * Poor man's update function. First deletes the entry and
	 * then inserts the updated row.
	 * 
	 * @param entity  The entry containing updated data.
	 * @return  The ID of the updated entry.
	 */
	def Integer update(Entity entity) {

		delete(entity)
		save(entity)
	}
	
	/**
	 * Saves the Entity if the it's Id field is {@code null}. Updates it 
	 * otherwise.
	 * If no field is @Id tagged, the Entity will be saved.
	 * 
	 * @param entity  An Entity that shall be saved or updated.
	 */
	def Integer persist(Entity entity) {
		if (getIdFieldValue(entity) == null) save(entity) else update(entity)
	}
		
	/**
	 * Deletes an Entity from the database. The Entity must have it's
	 * @Id tagged column set so some non-null value.
	 * 
	 * @param entity
	 */
	def void delete(Entity entity) {

		// TODO: Null Check.
		def name = getIdFieldName(entity?.class)
		def value = entity."${name}"
		// TODO: Throws ?
		connection.execute(
			"DELETE FROM " + entity?.class.simpleName + 
			" WHERE " + name + " = ${value}"
		)
	}

	/**
	 * Maps a single {@link GroovyRowResult} to its entity class. Copies all  
	 * columns of the row to their equally named properties of the class.
	 * 	
	 * @param result  A GroovyRowResult
	 * @param clazz  The entity class, to which the row will be mapped to.
	 * @return  An Entity with all columns as properties.
	 */
	private def <T extends Entity> T asClass(
		GroovyRowResult result, 
		Class<T> clazz) 
	{

		def instance = clazz.newInstance()

		result.keySet().each { instance."${it}" = result[it] }
		return instance
	}

//	private def <T extends Entity> T asClassNew(GroovyRowResult result, Class<T> clazz) {
//		
//		def instance = clazz.newInstance()
//				
//		for (Field field : clazz.declaredFields) {
//			
//			def name = field.name
//			// Check if field has OneToMany Annotation
//			if (hasOneToManyAnnotation(field)) {
//				
//				def fId = result[getIdFieldName(clazz)]
//				def fKey = field.getAnnotation(OneToMany).value
//				def fType = // Get field type
//				
//				instance."${name}" = query(
//					"SELECT * FROM " + fType +
//					" WHERE " + fKey + " = ${fId}",
//					fType
//				) 
//				
//				// Update reverse reference.
//				instance."${name}".each { e ->
//					e."${fKey}" = instance
//				}
//					
//			} else {
//				instance."${name}" = result[name]
//			}
//		}
//		
//		result.keySet().each { instance."${it}" = result[it] }
//		return instance
//	}
		
	/**
	 * selects all valid properties of an entity class. The properties 'class'
	 * and 'metaClass' will be ignored and CANNOT be used as column names.
	 *
	 * @param entity  An Entity.
	 * @return  A map of properties without 'class' and 'metaClass' properties.
	 */
	private def Map getProperties(Entity entity) {

		return entity.properties.findAll { key, value ->
			!(key in ['class', 'metaClass'])
		}
	}
}
