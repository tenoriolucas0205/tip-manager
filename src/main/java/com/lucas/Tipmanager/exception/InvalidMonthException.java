package com.lucas.Tipmanager.exception;

public class InvalidMonthException extends RuntimeException {

    public InvalidMonthException(int month) {
        super("Invalid month: " + month);
    }
}