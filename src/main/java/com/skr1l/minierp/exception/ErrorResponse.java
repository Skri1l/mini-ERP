package com.skr1l.minierp.exception;

public record ErrorResponse(
        int status,
        String message
)
{}
