package validators;

import org.openclassroom.projet.model.database.usager.Usager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import services.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Usager.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Usager usager = (Usager) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (usager.getUsername().length() < 6 || usager.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(usager.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (usager.getPassword().length() < 8 || usager.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        /*if (!usager.getConfirmPassword().equals(usager.getPassword())) {
            errors.rejectValue("confirmPassword", "Diff.userForm.confirmPassword");
        }*/
    }
}
