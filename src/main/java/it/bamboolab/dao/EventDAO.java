package it.bamboolab.dao;

import it.bamboolab.model.Event;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.List;

@Repository
public class EventDAO {

    private static final Logger logger = Logger.getLogger(JdbcUserDao.class);
    
    private SessionFactory sessionFactory;

    // Template as a properties
    private JdbcTemplate jdbcTemplate;

    // Empty costructor
    public EventDAO(){}

    public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
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

    	/*
        String sql = "SELECT * FROM event";

        List<Event> events  = jdbcTemplate.query(
        			sql,
        			new BeanPropertyRowMapper(Event.class)
                );

        return events;
        */
    	Session session = this.sessionFactory.getCurrentSession();
		List<Event> eventList = session.createQuery("from event").list();
		for(Event e : eventList){
			logger.info("Event List::" + e);
		}
		return eventList;
        
    }
    
    public List<Event> listAll() {

    	
    	Session session = this.sessionFactory.getCurrentSession();
		List<Event> eventList = session.createQuery("from event").list();
		for(Event e : eventList){
			logger.info("Event List::" + e);
		}
		return eventList;
    	
    }
}
