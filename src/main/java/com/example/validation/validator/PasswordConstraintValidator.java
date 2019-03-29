package com.example.validation.validator;

import com.example.validation.annotation.ValidPassword;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        PasswordValidator validator = new PasswordValidator(List.of(
                new LengthRule(8, 30),
                new UppercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new SpecialCharacterRule(1),
                new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }

        context.disableDefaultConstraintViolation(); // wyłączenie domyślnego komunikatu
        validator.getMessages(result).forEach(s -> { // dodanie szczegółowych błędów do wyniku
            context.buildConstraintViolationWithTemplate(s).addConstraintViolation();
        });

        return false;
    }
}
