package model

import static model.annotations.AnnotationUtils.*
import groovy.sql.GroovyRowResult
import groovy.sql.Sql

/**
 * Definition of the model, logic and persistence functions.
 */
class Model {

	private def Sql connection

	Model(String database, String user, String password, String driver) {
		this.connection = Sql.newInstance(database, user, password, driver)
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
	 * Issues a select query and transforms the retrieved rows to appropriate
	 * Entity classes.
	 * 
	 * @param sql  The SQL select query.
	 * @param clazz  The Entity class type the rows will be mapped to.
	 * @return  A List of Entities.
	 */
	def <T extends Entity> List<T> query(String sql, Class<T> clazz) {
		return connection.rows(sql).collect { row -> asClass(row, clazz) }
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
	
	/**
	 * Issues a select query and maps the retrieved single row to its
	 * appropriate Entity class.
	 * 
	 * @param sql  The SQL select query.
	 * @param clazz  The Entity class type the row will be mapped to.
	 * @return  A single Entity.
	 */
	def <T extends Entity> T queryFirst(String sql, Class<T> clazz) {
		return { r -> (r.empty) ? null : r?.first() }( query(sql, clazz) )
	}

	/**
	 * Retrieves the Entity row with a specific Id.
	 * 
	 * @param id  The Id of the Entity to be retrieved.
	 * @param clazz  The Entity's class type.
	 * @return  Entity instance of the row.
	 */
	def <T extends Entity> T get(Integer id, Class<T> clazz) {

		def name = getIdFieldName(clazz)
		return queryFirst(
			"SELECT * FROM " + clazz?.simpleName + 
			" WHERE " + name + " = ${id}",
			clazz
		)
	}

	/**
	 * Saves an Entity and returns the Id of the Entry in the database.
	 * 
	 * @param entity  An Entity.
	 * @return  The Id of the Entity's row in the database.
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
		
		// Insert data and retrieve assigned id.
		def id = connection.executeInsert(
			"INSERT INTO " + entity?.class.simpleName + 
			" (" + colStr + ") VALUES (" + valStr + ")", 
			values
		)
		
		// id is List<List<Integer>> in our case. 
		// Get rid of the extra lists.
		id?.first()?.first()
	}

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
}
