package br.com.dev.jsongenerator.exceptions;

import org.apache.http.HttpException;

public class IntegrationException extends HttpException {
    private static final long serialVersionUID = -9169754960841219015L;

    public IntegrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
