package br.com.dev.jsongenerator.dto;

import br.com.dev.jsongenerator.enums.FormatterEnum;
import br.com.dev.jsongenerator.enums.TypeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ObjectReaderDto implements Serializable {

    private static final long serialVersionUID = 545007088535058171L;

    private String property;
    private TypeEnum type;
    private Object value;

    /** Caso o objeto seja um array */
    private Integer size;


    private FormatterEnum formatter;

    private Boolean isNull;

}
