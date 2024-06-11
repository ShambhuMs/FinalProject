package com.xworkz.finalProject.webInitializer;

import com.xworkz.finalProject.configuration.DatabaseConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{DatabaseConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }
}
