package model

/**
 * Specifies static methods and fields to mimic an internal SQL DSL.
 * 
 * @author alexandra
 * @author carmen
 * @author mathias
 */
class Query {
	
	private static StringBuilder query
	
	static final String all = '*'
	
	/**
	 * 
	 * @see Model#query(String, Class)
	 * 
	 * @param model
	 * @return
	 */
	static def on(Model model) {
		
		query = new StringBuilder()
		[from : { Class clazz -> from(model, clazz) }]
	}
	
	/**
	 * 
	 * 
	 * @param model
	 * @param selection
	 * @return
	 */
	private static def select(Model model, String selection) {
		
		query << 'SELECT '
		query << selection
		[from : { Class clazz -> from(model, clazz) }]
	}
	
	/**
	 * 
	 * 	
	 * @param model
	 * @param clazz
	 * @return
	 */
	private static def from(Model model, Class clazz) {
		
		query << ' FROM '
		query << clazz.name
		[where   : { String cond -> where(model, clazz, cond) },
		 orderBy : { String ord  -> order(model, clazz, ord)  },
		 end     : model.query(query, clazz)]
	}
	
	/**
	 * 
	 * 
	 * @param model
	 * @param clazz
	 * @param cond
	 * @return
	 */
	private static def where(Model model, Class clazz, String cond) {
		
		query << ' WHERE '
		query << cond
		[orderBy : { String ord  -> order(model, clazz, ord) },
		 end     : model.query(query, clazz)]
	}
	
	/**
	 * 
	 * 
	 * @param model
	 * @param clazz
	 * @param ord
	 * @return
	 */
	private static def order(Model model, Class clazz, String ord) {
		
		query << ' ORDER BY '
		query << ord
		[end : model.query(query, clazz)]
	}
	
	/**
	 * 
	 * @see Model#save(Entity)
	 * 
	 * @param entity
	 * @return
	 */
	static def save(Entity entity) {
		[to : {Model model -> model.save(entity) }]
	}

	/**
	 * 
	 * @see Model#persist(Entity)
	 * 
	 * @param entity
	 * @return
	 */
	static def persist(Entity entity) {
		[to : {Model model -> model.persist(entity) }]
	}
	
	/**
	 * 
	 * @see Model#update(Entity)
	 * 	
	 * @param entity
	 * @return
	 */
	static def update(Entity entity) {
		[on : {Model model -> model.update(entity) }]
	}
	
	/**
	 * 
	 * @see Model#delete(Entity)
	 * 
	 * @param entity
	 * @return
	 */
	static def delete(Entity entity) {
		[on : {Model model -> model.delete(entity) }]
	}
	
	/**
	 * 
	 * @see Model#get(Integer, Class)
	 * 
	 * @param id
	 * @return
	 */
	static def get(Integer id) {
		[from : {Class clazz -> 
			[on : {Model model -> model.get(id, clazz) }] 
		}]
	}
}
