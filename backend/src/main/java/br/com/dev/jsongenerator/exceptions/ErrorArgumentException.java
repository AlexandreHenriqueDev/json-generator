package br.com.dev.jsongenerator.exceptions;

public class ErrorArgumentException extends RuntimeException {

    private static final long serialVersionUID = -363125346023337780L;

    public ErrorArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
