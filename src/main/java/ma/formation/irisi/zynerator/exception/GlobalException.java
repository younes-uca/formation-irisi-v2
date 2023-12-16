package ma.formation.irisi.zynerator.exception;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class GlobalException extends Exception implements Serializable {
    /**
     *
     */
    public final String FOREIGN_KEY_VIOLATION = "23503";
    public final String UNIQUE_VIOLATION = "23505";
    public final String NOT_NULL_VIOLATION = "23502";

    private String message;
    private HttpStatus status;

    public GlobalException() {

    }

    public GlobalException(Exception e, MessageSource messageSource, String requestURI) {
        status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (e instanceof ClientAbortException) {
            status = HttpStatus.CONFLICT;
            message = messageSource.getMessage("accesNetwork.message.texte", null, null);
        } else if (e instanceof BusinessRuleException) {
            BusinessRuleException businessRuleException = (BusinessRuleException) e;
            status = HttpStatus.CONFLICT;
            message = messageSource.getMessage(businessRuleException.getMessage(), businessRuleException.getParams(), null);
        } else if (e instanceof EntityNotFoundException) {
            EntityNotFoundException entityNotFoundException = (EntityNotFoundException) e;
            status = HttpStatus.NOT_FOUND;
            message = messageSource.getMessage(entityNotFoundException.getMessage(), entityNotFoundException.getParams(), null);
        } else if (e instanceof TimeoutException) {
            TimeoutException entityNotFoundException = (TimeoutException) e;
            status = HttpStatus.REQUEST_TIMEOUT;
            message = messageSource.getMessage(entityNotFoundException.getMessage(), entityNotFoundException.getParams(), null);
        } else if (e instanceof EntityAlreadyExistsException) {
            EntityAlreadyExistsException entityAlreadyExistsException = (EntityAlreadyExistsException) e;
            status = HttpStatus.CONFLICT;
            message = messageSource.getMessage(entityAlreadyExistsException.getMessage(), entityAlreadyExistsException.getParams(), null);
        } else {
            message = messageSource.getMessage("erreur.message.texte", null, null);
            status = HttpStatus.INTERNAL_SERVER_ERROR;


        }

    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
