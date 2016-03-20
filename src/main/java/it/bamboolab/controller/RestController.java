package it.bamboolab.controller;

import it.bamboolab.dao.EventDAO;
import it.bamboolab.dao.JdbcUserDao;
import it.bamboolab.dao.LdapUserDao;
import it.bamboolab.model.Event;
import it.bamboolab.model.User;
import it.bamboolab.utils.ApplicationProperties;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;

@Controller
public class RestController {

	@Autowired
	private JdbcUserDao userDao;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private LdapUserDao ldapUserDao;

    @RequestMapping(value="/alfresco/verify", method=RequestMethod.GET)
    public ModelAndView verifyAlfrescoUser(@RequestParam(value="userid") String userId, @RequestParam(value="usertype") String userType) {

        String urlToRead = ApplicationProperties.getProperty("alfrescoUserVerifyUrl", "http://www.test.com/");
        String httpAuthUser = ApplicationProperties.getProperty("alfrescoUserName", "");
        String httpAuthPass = ApplicationProperties.getProperty("alfrescoPassword", "");

        String result = "";

        try {

            URL url = new URL(String.format(urlToRead, userId, userType));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Basic authentication
            if (!httpAuthUser.equals("") && !httpAuthPass.equals("")) {
                String up = httpAuthUser + ":" + httpAuthPass;
                String encoded = Base64.getEncoder().encodeToString(up.getBytes());
                conn.setRequestProperty("Authorization", "Basic " + encoded);
            }

            conn.setReadTimeout(30000);

            InputStream is = conn.getInputStream();
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            result = sb.toString();
            System.out.println("Alfresco response = " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("json");
        mav.addObject("json", result);

        return mav;
    }

    @RequestMapping(value="/alfresco/update", method=RequestMethod.GET)
    public ModelAndView updateAlfrescoUser(
            @RequestParam(value="userid") String userId,
            @RequestParam(value="usertype") String userType,
            @RequestParam(value="ragionesociale") String ragioneSociale,
            @RequestParam(value="referente") String referente) {

        String urlToRead = ApplicationProperties.getProperty("alfrescoUrl", "");
        String httpAuthUser = ApplicationProperties.getProperty("alfrescoUserName", "");
        String httpAuthPass = ApplicationProperties.getProperty("alfrescoPassword", "");


        if (userType.equals("DIST_EST")) {
            userType = "1";
        } else if (userType.equals("DIST_ITA")) {
            userType = "2";
        } else if (userType.equals("RETAILER")) {
            userType = "3";
        }

        URL url;
        HttpURLConnection conn;
        String line;
        String result = "";

        try {
            userId = URLEncoder.encode(userId, "UTF-8");
            userType = URLEncoder.encode(userType, "UTF-8");
            ragioneSociale = URLEncoder.encode(ragioneSociale, "UTF-8");
            referente = URLEncoder.encode(referente, "UTF-8");

            urlToRead = String.format(urlToRead, userId, userType, ragioneSociale, referente);

            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Basic authentication
            if (!httpAuthUser.equals("") && !httpAuthPass.equals("")) {
                String up = httpAuthUser + ":" + httpAuthPass;
                String encoded = Base64.getEncoder().encodeToString(up.getBytes());
                conn.setRequestProperty("Authorization", "Basic " + encoded);
            }

            conn.setReadTimeout(30000);

            InputStream is = conn.getInputStream();
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            result = sb.toString();

            System.out.println("Alfresco response = " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("json");
        mav.addObject("json", result);

        return mav;
    }

    @RequestMapping(value="/userrole/get", method=RequestMethod.GET)
	public ModelAndView getRoleByUsername(@RequestParam(value="username") String username) {

		JSONObject obj = new JSONObject();
		try {
			String role = userDao.getRole(username);
			obj.put("status","OK");
			obj.put("role", role);

		} catch (EmptyResultDataAccessException e) {
			obj.put("status", "KO");
			obj.put("message", "Nessun ruolo trovato per lo username " + username);
		} catch (IncorrectResultSizeDataAccessException e) {
			obj.put("status", "KO");
			obj.put("message", "Nessun ruolo trovato per lo username " + username);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("json");
		mav.addObject("json", obj.toString());

		return mav;
	}



    @RequestMapping(value="/user/checkemail", method=RequestMethod.GET)
    public ModelAndView checkUserEmail(@RequestParam(value="email") String email) {

        JSONObject obj = new JSONObject();
        List<User> users = userDao.getUserByEmail(email);

        if(users.size()>0){

            obj.put("status", "KO");
            obj.put("message", "La mail " + email + " non risulta disponibile.");

        }else{
            obj.put("status","OK");
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("json");
        mav.addObject("json", obj.toString());

        return mav;
    }



    @RequestMapping(value="/users/all", method=RequestMethod.GET)
    public ModelAndView getAllUsers() {

        JSONObject obj = new JSONObject();
        List<User> users = userDao.getUserList();

        if(users.size()>0){
            obj.put("status","OK");
            obj.put("users",users);
        }else{
            obj.put("status", "KO");
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("json");
        mav.addObject("json", obj.toString());

        return mav;
    }

    @RequestMapping(value="/events/all", method=RequestMethod.GET)
    public ModelAndView getAllEvents() {

        JSONObject obj = new JSONObject();
        List<Event> eventList = eventDAO.getAll();

        if(eventList.size()>0){
            obj.put("status","OK");
            obj.put("events",eventList);
        }else{
            obj.put("status", "KO");
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("json");
        mav.addObject("json", obj.toString());

        return mav;
    }



    /*
        Experimenting oData services
     */

/*

    private EntityCollection getProducts(){

        EntityCollection retEntitySet = new EntityCollection();


        List<Event> eventList = eventDAO.getAll();

        for(Event eventEntity : eventList){
            retEntitySet.getEntities().add(eventEntity);
        }

        return retEntitySet;
    }

*/
}
