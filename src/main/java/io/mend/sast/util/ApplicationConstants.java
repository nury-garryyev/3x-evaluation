package io.mend.sast.util;

import java.util.Arrays;
import java.util.List;

public class ApplicationConstants {

    public static final String SERVER_ERROR = "Internal Server Error";
    public static final String USER_ERROR = "Something went wrong. Please contact customer care.";
    public static final String EMAIL_ADDRESS = "test@mail.com";
    public static final int USER_ROLE = 0;
    public static final int ADMIN_ROLE = 1;

    public static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("image/jpeg", "image/png");
    public static final String UPLOAD_DIRECTORY = "/app/uploads";

    private ApplicationConstants() {
    }
}
