package it.bamboolab.dao;

import it.bamboolab.model.Event;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.util.List;

@Repository
@Transactional
public class EventDAO {

    private static final Logger logger = Logger.getLogger(JdbcUserDao.class);
    
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
    
    public List<Event> listAll() {

    	Session session = this.sessionFactory.getCurrentSession();
		
    	List<Event> eventList = session.createQuery("from events").list();
		
		return eventList;
    	
    }
}
