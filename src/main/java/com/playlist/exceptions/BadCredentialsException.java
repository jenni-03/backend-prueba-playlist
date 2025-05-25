package com.playlist.exceptions;

import lombok.Getter;

@Getter
public class BadCredentialsException extends RuntimeException {
    private final String messageKey;
    private final Object[] args;

    public BadCredentialsException(String messageKey, Object... args) {
        super(messageKey);
        this.messageKey = messageKey;
        this.args = args;
    }
}
