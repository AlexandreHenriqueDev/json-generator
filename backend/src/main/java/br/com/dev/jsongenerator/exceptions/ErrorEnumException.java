package br.com.dev.jsongenerator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErrorEnumException extends EnumConstantNotPresentException {

    private static final long serialVersionUID = 7413174319714044883L;

    /**
     * Constructs an {@code EnumConstantNotPresentException} for the
     * specified constant.
     *
     * @param enumType     the type of the missing enum constant
     * @param constantName the name of the missing enum constant
     */
    public ErrorEnumException(Class<? extends Enum> enumType, String constantName) {
        super(enumType, constantName);
    }
}
