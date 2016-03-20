package it.bamboolab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

// Test Comment
@Controller
public class LoginController {

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal) {

		String name = principal.getName();
		model.addAttribute("username", name);
		return "main_page";

	}

	/**
	 *
	 * @author N4N
	 * @param model
	 * @param appEnv
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelMap model, @RequestParam(value = "appEnv", required = false) String appEnv) {
	//public ModelAndView login() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("login_page");


		String error = null;
/*

        appEnv = ApplicationProperties.getProperty("appEnvironmet", "?");
		appEnv = appEnv.trim();
		String descEnv = "";
		if (appEnv.equals("D")) {
			descEnv = "Development Environment";
		} else {
			if (appEnv.equals("T")) {
				descEnv = "Test Environment";
			} else {
				if (appEnv.equals("P")) {
					descEnv = "Production Environment";
				} else {
					error = "IL PARAMETRO appEnvironmet NON E' VALORIZZATO CORRETTAMENTE nel file di properties";
				}
			}
		}

		mav.addObject("descEnv", descEnv);
		*/
        mav.addObject("loginError", error);

		return mav;

	}

	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	public String loginError(ModelMap model) {
		model.addAttribute("loginError", "ERROR!!!");
		return "login_page";

	}
}