package io.github.censodev.vrms.vrmsserver.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Slf4j
public class RandomUtil {
    public static String generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        try {
            var random = SecureRandom.getInstanceStrong();
            char[] password = new char[length];

            password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
            password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
            password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
            password[3] = numbers.charAt(random.nextInt(numbers.length()));

            for (int i = 4; i < length; i++) {
                password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
            }
            return new String(password);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            return "";
        }
    }
}
