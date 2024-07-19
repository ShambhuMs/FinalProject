package com.xworkz.finalProject.components;

import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PasswordExpiryScheduler {
    @Autowired
    private SignUpService signUpService;

    @Scheduled(fixedRate = 10) // runs every 60 seconds     15*6*1000
    public void schedulePasswordInvalidation(){
        log.info("Running schedulePasswordInvalidation.....");
        signUpService.invalidateExpiredPasswords();
    }
}
