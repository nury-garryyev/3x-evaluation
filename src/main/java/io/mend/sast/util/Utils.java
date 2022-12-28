package io.mend.sast.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Utils {

    public static int getRandomNumber(int upperBound) {

        int randomInt = 0;

        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            randomInt = secureRandom.nextInt(upperBound);
        } catch (NoSuchAlgorithmException ignored) {

        }

        return randomInt;
    }

    private Utils() {
    }
}
