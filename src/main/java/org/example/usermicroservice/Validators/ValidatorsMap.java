package org.example.usermicroservice.Validators;

import lombok.Getter;
import org.example.usermicroservice.Validators.validatorImpls.EmailValidator;
import org.example.usermicroservice.Validators.validatorImpls.LoginValidator;
import org.example.usermicroservice.Validators.validatorImpls.PasswordValidator;
import org.example.usermicroservice.Validators.validatorImpls.UsernameValidator;

import java.util.HashMap;
import java.util.Map;
@Getter
public class ValidatorsMap {
    private Map<String, Validator> validators;
    public ValidatorsMap() {
        validators = new HashMap<String, Validator>();
        validators.put("login", new LoginValidator());
        validators.put("password", new PasswordValidator());
        validators.put("email", new EmailValidator());
        validators.put("username", new UsernameValidator());
    }
}
