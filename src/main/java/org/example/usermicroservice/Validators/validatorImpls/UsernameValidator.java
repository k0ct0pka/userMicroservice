package org.example.usermicroservice.Validators.validatorImpls;

import org.example.usermicroservice.Validators.Validator;
import org.example.usermicroservice.exceptions.ValidatorException;

public class UsernameValidator  implements Validator {
    @Override
    public void validate(String string) throws ValidatorException {
        if(string == null || string.isEmpty())
            throw new ValidatorException("Username cannot be empty");
        if(string.length() < 6)
            throw new ValidatorException("Username must be at least 6 characters");
        if(!Character.isLetter(string.charAt(0)))
            throw new ValidatorException("Username must start with a letter");
        if(string.length() > 20)
            throw new ValidatorException("Username cannot contain more than 20 characters");
    }
}
