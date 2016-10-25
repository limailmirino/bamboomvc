package it.bamboolab.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Enrico on 11/11/2015.
 */
public class UserValidator implements Validator {
    
	public boolean supports(Class<?> aClass) {
        return false;
    }

    
    public void validate(Object o, Errors errors) {

    }
}
