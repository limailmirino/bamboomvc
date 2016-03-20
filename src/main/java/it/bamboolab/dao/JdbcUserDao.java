package it.bamboolab.dao;

import it.bamboolab.model.Event;
import it.bamboolab.model.User;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcUserDao {

    private static final Logger logger = Logger.getLogger(JdbcUserDao.class);

    private String referencePerson;

    private JdbcTemplate jdbcTemplate;


    public JdbcUserDao() {}

    public JdbcUserDao(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));

            return user;
        }
    }

    public List<User> getUserList() {

        String sql = "SELECT * FROM users";

        List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {

            public User mapRow(ResultSet rs, int rowNum) throws SQLException {

                User user = new User();

                user.setRole(rs.getString("role"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCreationDate(rs.getString("creation_date"));
                user.setRevisionDate(rs.getString("revision_date"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setSapCode(rs.getString("sap_code"));
                user.setEmail(rs.getString("email"));
                user.setGoLiveDate(rs.getString("golive_date"));
                user.setIdRefPerson(rs.getInt("id_refPerson"));
                user.setDn(rs.getString("guid"));

                return user;
            }
        });

        return users;
    }

    public List<User> search(String  where, String ord) {

        String sql = "SELECT name, surname, username, email, sap_code, company_name FROM users " + where + " ORDER BY " + ord + " ASC";

        List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {

            public User mapRow(ResultSet rs, int rowNum) throws SQLException {

                User user = new User();

                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setSapCode(rs.getString("sap_code"));
                user.setCompany(rs.getString("company_name"));
                user.setEmail(rs.getString("email"));

                return user;
            }
        });

        return users;
    }

    public void saveUser(User user) {

        String sql = "INSERT INTO users (username, password, creation_date, revision_date, name, surname, sap_code, company_name, email, golive_date, role, guid, id_refPerson) VALUES (?, ?, NOW(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            this.jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getRevisionDate(), user.getName(), user.getSurname(), user.getSapCode(), user.getCompany(), user.getEmail(), user.getGoLiveDate(), user.getRole(), user.getDn(), user.getIdRefPerson());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void deleteUser(String uid) {

        String sql = "DELETE FROM users WHERE username=?";

        try {
            this.jdbcTemplate.update(sql, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsername(String username) {

        String sql = "SELECT id, username, password, creation_date, revision_date, name, surname, sap_code, email, golive_date, role, guid, id_refPerson FROM users WHERE username=?";

        List<User> users = jdbcTemplate.query(sql, new Object[]{username}, new RowMapper<User>() {

            public User mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {

                User usr = new User();

                usr.setRole(rs.getString("role"));
                usr.setUsername(rs.getString("username"));
                usr.setPassword(rs.getString("password"));
                usr.setCreationDate(rs.getString("creation_date"));
                usr.setRevisionDate(rs.getString("revision_date"));
                usr.setName(rs.getString("name"));
                usr.setSurname(rs.getString("surname"));
                usr.setSapCode(rs.getString("sap_code"));
                usr.setEmail(rs.getString("email"));
                usr.setGoLiveDate(rs.getString("golive_date"));
                usr.setIdRefPerson(rs.getInt("id_refPerson"));



                return usr;
            }
        });

        if(users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }

    public List<User> getUserByEmail(String email) {

        String sql = "SELECT * FROM users WHERE email=?";

        List<User> users = jdbcTemplate.query(sql, new Object[]{email}, new RowMapper<User>() {

            public User mapRow(ResultSet rs, int rowNum) throws SQLException {

                User usr = new User();

                usr.setRole(rs.getString("role"));
                usr.setUsername(rs.getString("username"));
                usr.setPassword(rs.getString("password"));
                usr.setCreationDate(rs.getString("creation_date"));
                usr.setRevisionDate(rs.getString("revision_date"));
                usr.setName(rs.getString("name"));
                usr.setSurname(rs.getString("surname"));
                usr.setSapCode(rs.getString("sap_code"));
                usr.setEmail(rs.getString("email"));
                usr.setGoLiveDate(rs.getString("golive_date"));
                usr.setIdRefPerson(rs.getInt("id_refPerson"));

                return usr;
            }
        });

        return users;
    }

    public List<User> getUserWhithNullPassword() {

        String sql = "SELECT * FROM users WHERE password='null'";

        List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {

            public User mapRow(ResultSet rs, int rowNum) throws SQLException {

                User user = new User();

                user.setRole(rs.getString("role"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCreationDate(rs.getString("creation_date"));
                user.setRevisionDate(rs.getString("revision_date"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setSapCode(rs.getString("sap_code"));
                user.setEmail(rs.getString("email"));
                user.setGoLiveDate(rs.getString("golive_date"));
                user.setIdRefPerson(rs.getInt("id_refPerson"));

                return user;
            }
        });

        return users;
    }

    public String getPasswordFix(String username) {
        String sql = "SELECT password FROM fix_password WHERE username=?";
        String password = (String) jdbcTemplate.queryForObject(sql, new Object[] {username}, String.class);
        return password;
    }

    public String getUsernameBySapCode(String sapCode) {
        String sql = "SELECT username FROM users WHERE sap_code=?";
        String uname = (String) jdbcTemplate.queryForObject(sql, new Object[] {sapCode}, String.class);
        return uname;
    }


    public void updateUser(User user){

        String sql = "UPDATE users SET " +
                            "username=?, " +
                            "password=?, " +
                            "creation_date=?, " +
                            "revision_date=?, " +
                            "name=?, " +
                            "surname=?, " +
                            "sap_code=?, " +
                            "email=?, " +
                            "golive_date=?, " +
                            "role=?, " +
                            "guid=?, " +
                            "id_refPerson=? " +
                            "WHERE username=?";
        this.jdbcTemplate.update(sql, new Object[] {user.getUsername(),
                                                    user.getPassword(),
                                                    user.getCreationDate(),
                                                    user.getRevisionDate(),
                                                    user.getName(),
                                                    user.getSurname(),
                                                    user.getSapCode(),
                                                    user.getEmail(),
                                                    user.getGoLiveDate(),
                                                    user.getRole(),
                                                    user.getDN(),
                                                    user.getIdRefPerson(),
                                                    user.getUsername()});

    }

    public String getReference(String username){
        String sql = "SELECT id_user FROM refs r, users u WHERE u.id_refPerson = r.id AND u.username=?";
        String ref = (String) jdbcTemplate.queryForObject(sql, new Object[] {username}, String.class);
        return ref;
    }

    public String getRole(String username){
        String sql = "SELECT role FROM users WHERE username=?";
        String role = (String) jdbcTemplate.queryForObject(sql, new Object[] {username}, String.class);
        return role;
    }

    public void changePassword(User user) {

        String sql = "UPDATE users set password=? WHERE username=?";
        this.jdbcTemplate.update(sql, user.getPassword(), user.getUsername());
    }

    public void updateCompany(String userName, String companyName) {
        String sql = "UPDATE users set company_name=? WHERE username=?";
        this.jdbcTemplate.update(sql, companyName, userName);
    }

    public void updateGuid(String userName, String guid) {
        String sql = "UPDATE users set guid=? WHERE username=?";
        this.jdbcTemplate.update(sql, guid, userName);
    }
}
