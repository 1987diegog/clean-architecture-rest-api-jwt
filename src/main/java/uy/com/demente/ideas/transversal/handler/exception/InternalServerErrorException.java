package uy.com.demente.ideas.transversal.handler.exception;


import uy.com.demente.ideas.transversal.handler.response.ExceptionDetails;

public class InternalServerErrorException extends Exception {

    private ExceptionDetails exceptionDetails;

    public InternalServerErrorException() {
        super();
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerErrorException(ExceptionDetails exceptionDetails, Throwable cause) {
        super(cause);
        this.exceptionDetails = exceptionDetails;
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause);
    }

    public ExceptionDetails getExceptionDetails() {
        return exceptionDetails;
    }

    public void setExceptionDetails(ExceptionDetails exceptionDetails) {
        this.exceptionDetails = exceptionDetails;
    }
}