package it.bamboolab.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Enrico on 11/11/2015.
 */
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
