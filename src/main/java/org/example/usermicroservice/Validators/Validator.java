package org.example.usermicroservice.Validators;

import org.example.usermicroservice.exceptions.ValidatorException;

public interface Validator {
    void validate(String string) throws ValidatorException;
}
