package com.xworkz.finalProject.randomPassword;

import java.security.SecureRandom;

public class RandomPasswordGenerator {
    private static  final String CHARACTERS="0123456789";
    private static final int PASSWORD_LENGTH=6;
    private static final SecureRandom random=new SecureRandom();

    public static String generatePassword(){
        StringBuilder password=new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i <PASSWORD_LENGTH ; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }
}
