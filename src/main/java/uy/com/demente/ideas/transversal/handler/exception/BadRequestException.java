package uy.com.demente.ideas.transversal.handler.exception;


import uy.com.demente.ideas.transversal.handler.response.ExceptionDetails;

public class BadRequestException extends Exception {

    private ExceptionDetails exceptionDetails;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(ExceptionDetails exceptionDetails, Throwable cause) {
        super(cause);
        this.exceptionDetails = exceptionDetails;
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }


    public ExceptionDetails getExceptionDetails() {
        return exceptionDetails;
    }

    public void setExceptionDetails(ExceptionDetails exceptionDetails) {
        this.exceptionDetails = exceptionDetails;
    }
}
