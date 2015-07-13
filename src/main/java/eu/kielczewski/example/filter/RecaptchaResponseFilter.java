package eu.kielczewski.example.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

// Uncomment the following to see how the filter might work
//@Component
public class RecaptchaResponseFilter implements Filter {

    private static final String RECAPTCHA_RESPONSE_ALIAS = "recaptchaResponse";
    private static final String RECAPTCHA_RESPONSE_ORIGINAL = "g-recaptcha-response";

    private static class ModifiedHttpServerRequest extends HttpServletRequestWrapper {

        final Map<String, String[]> parameters;

        public ModifiedHttpServerRequest(HttpServletRequest request) {
            super(request);
            parameters = new HashMap<>(request.getParameterMap());
            parameters.put(RECAPTCHA_RESPONSE_ALIAS, request.getParameterValues(RECAPTCHA_RESPONSE_ORIGINAL));
        }

        @Override
        public String getParameter(String name) {
            return parameters.containsKey(name) ? parameters.get(name)[0] : null;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return parameters;
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return Collections.enumeration(parameters.keySet());
        }

        @Override
        public String[] getParameterValues(String name) {
            return parameters.get(name);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest
                && servletRequest.getParameter(RECAPTCHA_RESPONSE_ORIGINAL) != null) {
            filterChain.doFilter(new ModifiedHttpServerRequest((HttpServletRequest) servletRequest), servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

}