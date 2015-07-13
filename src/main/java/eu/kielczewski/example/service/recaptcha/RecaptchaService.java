package eu.kielczewski.example.service.recaptcha;

public interface RecaptchaService {

    boolean isResponseValid(String remoteIp, String response);

}
