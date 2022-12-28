package io.mend.sast.exception;

import java.io.Serial;

public class DatabaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4657491283614455649L;

    public DatabaseException(String msg) {
        super(msg);
    }

    public DatabaseException(String msg, Throwable t) {
        super(msg, t);
    }

}