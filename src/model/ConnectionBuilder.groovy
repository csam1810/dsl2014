package model

import java.util.Map;

class ConnectionParams {
	
	String host
	String database
	String user
	String password
	
	public String url() {
		return "jdbc:mysql://$host/$database?user=$user&password=$password"
	}
}

class ConnectionBuilder extends BuilderSupport {

	ConnectionParams instance = new ConnectionParams()
	
	@Override
	protected Object createNode(Object name) {
		switch (name) {
			case 'new':
				instance = new ConnectionParams()
				break
		}
		return this;
	}

	@Override
	protected Object createNode(Object name, Object value) {
		switch (name) {
			case 'host':
				instance.host = value
				break
			case 'database':
				instance.database = value
				break
			case 'user':
				instance.user = value
				break
			case 'password':
				instance.password = value
				break
		}
		return this;
	}

	@Override
	protected Object createNode(Object name, Map attributes) {
		return this;
	}

	@Override
	protected Object createNode(Object name, Map attributes, Object value) {
		return this;
	}

	@Override
	protected void setParent(Object parent, Object child) {}
}
