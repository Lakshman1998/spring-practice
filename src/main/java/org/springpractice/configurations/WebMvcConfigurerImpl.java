package org.springpractice.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "org.springpractice")
@EnableWebMvc
public class WebMvcConfigurerImpl implements WebMvcConfigurer {

}
