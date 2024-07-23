package com.xworkz.finalProject.configuration.mailConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {
    public EmailConfiguration() {
        System.out.println("Created no-arg const in EmailConfiguration");
    }


    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl emailSender=new JavaMailSenderImpl();
        emailSender.setHost("smtp.gmail.com");
        emailSender.setPort(587);
        emailSender.setUsername("shambhu88.xworkz@gmail.com");
        emailSender.setPassword("padh uebq sznx jpzr");
        Properties properties=emailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol","smtp");//simple mail transport protocol(SMTP)
        properties.put("mail.smtp.auth","true"); //authentication
        properties.put("mail.smtp.ssl.trust","smtp.gmail.com"); // only for gmail
        properties.put("mail.smtp.starttls.enable","true"); //all ready
        properties.put("mail.debug","true");
        return emailSender;
    }
}
