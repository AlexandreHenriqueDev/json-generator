package br.com.dev.jsongenerator.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = -1015113178861726653L;

    private Date timestamp;
    private String message;
    private String details;

}
