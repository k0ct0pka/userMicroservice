package org.example.usermicroservice.Validators.validatorImpls;

import org.example.usermicroservice.Validators.Validator;
import org.example.usermicroservice.exceptions.ValidatorException;

public class EmailValidator implements Validator {
    @Override
    public void validate(String string) throws ValidatorException {
        if(string == null || string.isEmpty()){
            throw new ValidatorException("Email address cannot be empty");
        }
        if(string.length()<4){
            throw new ValidatorException("Email address must have at least 4 characters");
        }
        if(!string.matches(".*@.*")){
            throw new ValidatorException("Email address must be a valid email address");
        }
    }
}
