package org.example.usermicroservice.Validators.validatorImpls;

import org.example.usermicroservice.Validators.Validator;
import org.example.usermicroservice.exceptions.ValidatorException;

public class LoginValidator implements Validator {
    @Override
    public void validate(String string) throws ValidatorException {
        if(string == null || string.isEmpty()){
            throw new ValidatorException("login cannot be empty");
        }
        if(string.length() < 5){
            throw new ValidatorException("login must be at least 5 characters");
        }
        if(string.length() > 16){
            throw new ValidatorException("login must be at most 16 characters");
        }
    }
}
