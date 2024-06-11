package com.xworkz.finalProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.xworkz.finalProject")
@EnableWebMvc
public class ResourceMappConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver(){
        System.out.println("Running ViewResolver in TravelConfig...");
      return new InternalResourceViewResolver("/",".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
     registry.addResourceHandler("/image/**")
             .addResourceLocations("/images/");
    }
}
