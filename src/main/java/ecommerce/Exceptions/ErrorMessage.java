package ecommerce.Exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorMessage {

    private Date timestamp;
    private String message;


    /**
     * Errors displays timestamp and message
     */

    public ErrorMessage(Date timestamp, String message) {
        super();
        this.timestamp = timestamp;
        this.message = message;
    }

}
