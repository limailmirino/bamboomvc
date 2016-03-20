package it.bamboolab.dao;

import it.bamboolab.model.Event;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by enrico on 12/13/15.
 */
public class EventDAO {

    private static final Logger logger = Logger.getLogger(JdbcUserDao.class);

    // Template as a properties
    private JdbcTemplate jdbcTemplate;

    // Empty costructor
    public EventDAO(){}

    // Datasource costructor
    public EventDAO(DataSource ds) {this.jdbcTemplate = new JdbcTemplate(ds);}

    // Datasource setter
    public void setDataSource(DataSource dataSource) {this.jdbcTemplate = new JdbcTemplate(dataSource);}
/*
    public List<Event> getAll() {

        String sql = "SELECT * FROM events";

        List<Event> events = jdbcTemplate.query(sql, new RowMapper<Event>() {

            public Event mapRow(ResultSet rs, int rowNum) throws SQLException {

                Event event = new Event();

                event.setId(rs.getString("id"));
                event.setTitle(rs.getString("title"));
                event.setDescription(rs.getString("description"));
                event.setLocation(rs.getString("location"));
                event.setStart(rs.getString("start"));
                event.setEnd(rs.getString("end"));
                event.setPictureSmall(rs.getString("pictureSmall"));

                return event;
            }
        });

        return events;
    }

    */


    public List<Event> getAll() {

        String sql = "SELECT * FROM events";

        List<Event> events  = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(Event.class));

        return events;
    }
}
