package com.coadon.almpp.almpp.commands;

public class InvalidCommandArgumentsException extends Exception {

    public InvalidCommandArgumentsException() {
        super();
    }

    public InvalidCommandArgumentsException(String message) {
        super(message);
    }

    public InvalidCommandArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCommandArgumentsException(Throwable cause) {
        super(cause);
    }
}
