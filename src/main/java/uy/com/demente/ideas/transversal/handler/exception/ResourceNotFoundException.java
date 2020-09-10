package uy.com.demente.ideas.transversal.handler.exception;

import uy.com.demente.ideas.transversal.handler.response.ExceptionDetails;

public class ResourceNotFoundException extends Exception {

    private ExceptionDetails exceptionDetails;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(ExceptionDetails exceptionDetails, Throwable cause) {
        super(cause);
        this.exceptionDetails = exceptionDetails;
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ExceptionDetails getExceptionDetails() {
        return exceptionDetails;
    }

    public void setExceptionDetails(ExceptionDetails exceptionDetails) {
        this.exceptionDetails = exceptionDetails;
    }
}