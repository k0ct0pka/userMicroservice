package org.example.usermicroservice.Validators.validatorImpls;

import org.example.usermicroservice.Validators.Validator;
import org.example.usermicroservice.exceptions.ValidatorException;
public class PasswordValidator implements Validator {
    public void validate(String password) {
        if (password.length() < 8) {
            throw new ValidatorException("Password must be at least 8 characters");
        }
        if (password.length() > 120) {
            throw new ValidatorException("Password must be at most 120 characters");
        }
        if(!(password.matches(".*[a-z].*") && password.matches(".*[0-9].*") && password.matches(".*[A-Z].*")&& password.matches(".*[!@#$%&*()_+=|<>?{}\\[\\]~-].*") && !password.matches(".*\\s.*"))) {
            throw new ValidatorException("Password must contain at least one lowercase letter and one uppercase letter and at most one special character and at most one digit and can not contain white space");
        }
    }
}
