package com.xworkz.finalProject.webInitializer;

import com.xworkz.finalProject.configuration.DatabaseConfig;
import com.xworkz.finalProject.configuration.FileUploadConfig;
import com.xworkz.finalProject.configuration.ResourceMapConfig;
import com.xworkz.finalProject.controller.AdminController;
import com.xworkz.finalProject.controller.SignInController;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer implements WebMvcConfigurer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{DatabaseConfig.class, ResourceMapConfig.class, FileUploadConfig.class,
                SignInController.class, AdminController.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        System.out.println("Running configureDefaultServletHandling method in WebInitializer class.....");
        configurer.enable();
    }
}
