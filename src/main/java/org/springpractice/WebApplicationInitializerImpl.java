package org.springpractice;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.http.HttpServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springpractice.configurations.WebMvcConfigurerImpl;
import org.springpractice.servlets.ResourceServlet;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WebApplicationInitializerImpl implements WebApplicationInitializer {

    private static final Logger LOGGER = Logger.getLogger(WebApplicationInitializerImpl.class.getCanonicalName());

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(WebMvcConfigurerImpl.class);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("app", dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/app/*");

        HttpServlet helloServlet = new ResourceServlet();
        ServletRegistration.Dynamic helloSerlvetRegistration = servletContext.addServlet("helloservlet", helloServlet);
        helloSerlvetRegistration.addMapping("/resource/*");

        LOGGER.log(Level.INFO, "========= Server start successfully =======");
    }
}