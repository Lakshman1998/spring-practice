package org.springpractice;

import com.google.common.base.Strings;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springpractice.configurations.WebMvcConfigurerImpl;

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
        String contextPath = servletContext.getContextPath();
        LOGGER.log(Level.INFO, String.format("Servlet context path: %s", contextPath));
        String servletMapping = Strings.isNullOrEmpty(contextPath) ? "/*" : String.format("/%s/*", contextPath);
        servletRegistration.addMapping(servletMapping);

        // Filter registration
//        FilterRegistration.Dynamic corsFilterRegistration = servletContext.addFilter("CorsFilter", new CorsFilter());
//        corsFilterRegistration.addMappingForUrlPatterns(null, false, "/app/*");

        LOGGER.log(Level.INFO, "========= Server start successfully =======");
    }
}