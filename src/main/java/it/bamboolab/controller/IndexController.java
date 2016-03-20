package it.bamboolab.controller;

import it.bamboolab.dao.EventDAO;
import it.bamboolab.dao.JdbcUserDao;
import it.bamboolab.model.Event;
import it.bamboolab.model.Role;
import it.bamboolab.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value="/index")
public class IndexController {

    @Autowired
    private JdbcUserDao jdbcUserDao;

    @Autowired
    private EventDAO eventDAO;


    @RequestMapping(method=RequestMethod.GET)
	public ModelAndView home(Principal principal) {
		
		ModelAndView mav = new ModelAndView();

        List<Event> eventList = eventDAO.getAll();

        mav.addObject("events", eventList);

		mav.setViewName("index");

        return mav;
	}

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView homeSearch(Principal principal,
                                   @RequestParam(value="name", required=false) String name,
                                   @RequestParam(value="role", required=false) String role,
                                   @RequestParam(value="cust_code", required=false) String cust_code,
                                   @RequestParam(value="email", required=false) String email,
                                   @RequestParam(value="error", required=false) String error) throws Exception {    // N4N 15/05/2015: LDAP Connection missing

        ModelAndView mav = new ModelAndView();

        String where = "";

        if(!name.equals("") || !role.equals("") || !cust_code.equals("") || !email.equals("")) {

            where = where + "WHERE username IS NOT NULL ";

            if(!role.equals("")) {
                where = where + "AND role='" + role + "' ";
            }

            if(!name.equals("")) {
                where = where + "AND (name LIKE '%" + name + "%' OR surname LIKE '%" + name + "%') ";
            }

            if(!cust_code.equals("")) {
                where = where + "AND sap_code LIKE '%" + cust_code + "%' ";
            }

            if(!email.equals("")) {
                where = where + "AND email LIKE '%" + email + "%' ";
            }
        }

        List<User> resList = jdbcUserDao.search(where, "company_name");


        mav.setViewName("index");

        mav.addObject("results", resList);

        mav.addObject("search_name", name);
        mav.addObject("search_role", role);
        mav.addObject("search_cust_code", cust_code);
        mav.addObject("search_email", email);

        mav.addObject("error", error);

        return mav;
    }
}
