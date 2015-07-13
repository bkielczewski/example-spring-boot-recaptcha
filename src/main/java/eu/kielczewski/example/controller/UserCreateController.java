package eu.kielczewski.example.controller;

import eu.kielczewski.example.domain.User;
import eu.kielczewski.example.domain.form.UserCreateForm;
import eu.kielczewski.example.service.user.UserService;
import eu.kielczewski.example.service.user.exception.UserAlreadyExistsException;
import eu.kielczewski.example.validator.RecaptchaFormValidator;
import eu.kielczewski.example.validator.UserCreateFormPasswordValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserCreateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateController.class);
    private final UserService userService;
    private final UserCreateFormPasswordValidator passwordValidator;
    private final RecaptchaFormValidator recaptchaFormValidator;

    @Autowired
    public UserCreateController(UserService userService, UserCreateFormPasswordValidator passwordValidator,
            RecaptchaFormValidator recaptchaFormValidator) {
        this.userService = userService;
        this.passwordValidator = passwordValidator;
        this.recaptchaFormValidator = recaptchaFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(passwordValidator);
        binder.addValidators(recaptchaFormValidator);
    }

    @ModelAttribute("recaptchaSiteKey")
    public String getRecaptchaSiteKey(@Value("${recaptcha.site-key}") String recaptchaSiteKey) {
        return recaptchaSiteKey;
    }

    @RequestMapping(value = "/user_create.html", method = RequestMethod.GET)
    public ModelAndView getCreateUserView() {
        LOGGER.debug("Received request for user_create view");
        return new ModelAndView("user_create", "form", new UserCreateForm());
    }

    @RequestMapping(value = "/user_create.html", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("form") @Valid UserCreateForm form, BindingResult result) {
        LOGGER.debug("Received request to user_create view, form={}, result={}", form, result);
        if (result.hasErrors()) {
            return "user_create";
        }
        try {
            userService.save(new User(form.getId(), form.getPassword2()));
        } catch (UserAlreadyExistsException e) {
            LOGGER.debug("Tried to create user with existing id", e);
            result.rejectValue("id", "user.error.id.exists");
            return "user_create";
        }
        return "redirect:/user_list.html";
    }

}
