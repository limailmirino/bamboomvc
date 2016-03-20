package it.bamboolab.dao;

import it.bamboolab.utils.ApplicationProperties;

import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;

public class LdapConnection {

	public static LDAPConnection getInstance() {
		LDAPConnection connection = null;
		try {
			String password = ApplicationProperties.getProperty("ldapPassword", "password");
			String username = ApplicationProperties.getProperty("ldapUsername", "CN=Manager,OU=my,OU=org");
			String server = ApplicationProperties.getProperty("ldapServer", "127.0.0.1");

			connection = new LDAPConnection(server, 389, username, password );
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return connection;
	}
	
}
