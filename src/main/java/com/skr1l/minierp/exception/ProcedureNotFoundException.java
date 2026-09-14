package com.skr1l.minierp.exception;

public class ProcedureNotFoundException extends RuntimeException {
    public ProcedureNotFoundException(String message) {
        super(message);
    }
}
