package com.atanava.restaurants.web.user;

import com.atanava.restaurants.HasIdAndEmail;
import com.atanava.restaurants.model.User;
import com.atanava.restaurants.repository.user.UserRepository;
import com.atanava.restaurants.web.ExceptionInfoHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

@Component
public class UniqueMailValidator implements org.springframework.validation.Validator {

    private final UserRepository repository;

    public UniqueMailValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return HasIdAndEmail.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HasIdAndEmail user = ((HasIdAndEmail) target);
        if (StringUtils.hasText(user.getEmail())) {
            User dbUser = repository.getByEmail(user.getEmail().toLowerCase());
            if (dbUser != null && !dbUser.getId().equals(user.getId())) {
                errors.rejectValue("email", ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL);
            }
        }
    }
}
