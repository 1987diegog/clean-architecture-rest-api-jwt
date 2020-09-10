package uy.com.demente.ideas.transversal.handler.exception;

import uy.com.demente.ideas.transversal.handler.response.ExceptionDetails;

public class ForbiddenException extends Exception {

    private static final long serialVersionUID = 1L;

    private ExceptionDetails exceptionDetails;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(ExceptionDetails exceptionDetails, Throwable cause) {
        super(cause);
        this.exceptionDetails = exceptionDetails;
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }

    public ExceptionDetails getExceptionDetails() {
        return exceptionDetails;
    }

    public void setExceptionDetails(ExceptionDetails exceptionDetails) {
        this.exceptionDetails = exceptionDetails;
    }
}