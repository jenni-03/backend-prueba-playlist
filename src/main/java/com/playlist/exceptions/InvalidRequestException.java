package com.playlist.exceptions;


import lombok.Getter;

@Getter
public class InvalidRequestException extends RuntimeException {
    private final String messageKey;
    private final Object[] args;

    public InvalidRequestException(String messageKey, Object... args) {
        super(messageKey);
        this.messageKey = messageKey;
        this.args = args;
    }

}