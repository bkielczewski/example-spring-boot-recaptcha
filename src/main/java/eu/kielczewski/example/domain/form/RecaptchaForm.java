package eu.kielczewski.example.domain.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class RecaptchaForm {

    @NotEmpty
    private String recaptchaResponse;

    public void setRecaptchaResponse(String response) {
        this.recaptchaResponse = response;
    }

    public String getRecaptchaResponse() {
        return recaptchaResponse;
    }

    @Override
    public String toString() {
        return "RecaptchaForm{" +
                "recaptchaResponse='" + recaptchaResponse + '\'' +
                '}';
    }
}
