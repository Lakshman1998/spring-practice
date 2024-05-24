package org.springpractice.filters;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springpractice.WebApplicationInitializerImpl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CorsFilter extends HttpFilter {

    private static final Logger LOGGER = Logger.getLogger(WebApplicationInitializerImpl.class.getCanonicalName());
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.log(Level.INFO, "Cors Filter execution started");
        String requestMethod = request.getMethod();
        if(requestMethod.equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
            response.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            response.addHeader(ACCESS_CONTROL_ALLOW_METHODS, "GET, OPTIONS, POST, PUT, DELETE");
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
        chain.doFilter(request, response);
    }
}
