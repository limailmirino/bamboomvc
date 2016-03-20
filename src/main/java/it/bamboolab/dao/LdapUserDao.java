package it.bamboolab.dao;

import com.unboundid.ldap.sdk.*;
import com.unboundid.ldap.sdk.controls.ServerSideSortRequestControl;
import com.unboundid.ldap.sdk.controls.SortKey;
import it.bamboolab.model.User;
import it.bamboolab.utils.ApplicationProperties;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

//A beatiful indian day!!
public class LdapUserDao {


    private static final Logger logger = Logger.getLogger(LdapUserDao.class);

    public List<SearchResultEntry> getAllActiveUsers() {

        LDAPConnection connection = LdapConnection.getInstance();
        SearchResult result;
        List<SearchResultEntry> resList = null;

        try {

            String base = "ou=Customers,dc=cellularline,dc=com";
            SearchRequest searchRequest = new SearchRequest(base, SearchScope.SUB, Filter.createEqualityFilter("objectClass", "inetOrgPerson"));

            result = connection.search(searchRequest);
            resList = result.getSearchEntries();

        } catch (LDAPSearchException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return resList;
    }

    public ArrayList<User> getUsers(String search) throws Exception {

        LDAPConnection connection = LdapConnection.getInstance();

        if (connection == null) {
            return null;
        }

        String filter = search;
        String base = ApplicationProperties.getProperty("userSearchBase", "DC=cellularline,DC=com");

        SearchResult result;

        List<SearchResultEntry> resList = null;
        ArrayList<User> users = new ArrayList<User>();

        try {

            SearchRequest searchRequest = new SearchRequest(base, SearchScope.SUB, Filter.createEqualityFilter("objectClass", "inetOrgPerson"));
            searchRequest.setFilter(filter);
            searchRequest.addControl(new ServerSideSortRequestControl(new SortKey("sn")));

            //result = connection.search(base, SearchScope.SUB, filter);
            result = connection.search(searchRequest);
            resList = result.getSearchEntries();

            for (SearchResultEntry entry : resList) {

                try {
                    User user = new User();

                    user.setCn(entry.getAttribute("cn").getValue());
                    user.setEmail(entry.getAttribute("mail").getValue());
                    user.setUsername(entry.getAttribute("uid").getValue());
                    user.setSapCode(entry.getAttribute("employeeNumber").getValue());
                    user.setCompany(entry.getAttribute("ou").getValue());
                    user.setRole(entry.getAttribute("employeeType").getValue());
                    user.setReferencePerson(entry.getAttribute("businessCategory").getValue());

                    users.add(user);

                }catch(NullPointerException e){
                    logger.error("User with a null value skipped", e);
                }
            }

        } catch (LDAPSearchException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return users;
    }

    public User getSingleDN(String dn) throws Exception {

        User user = null;

        LDAPConnection connection = LdapConnection.getInstance();
        if (connection == null) {
            return null;
        }

        SearchResultEntry entry = null;

        try {

            entry = connection.getEntry(dn);

        } catch (LDAPSearchException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return user;

    }

    public void deleteUser(String dn) throws Exception {

        DeleteRequest dr = new DeleteRequest(dn);
        LDAPConnection conn = LdapConnection.getInstance();

        try {
            conn.delete(dr);
        } catch (LDAPException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
