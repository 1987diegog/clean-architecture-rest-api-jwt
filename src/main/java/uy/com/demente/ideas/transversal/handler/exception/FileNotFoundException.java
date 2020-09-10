package uy.com.demente.ideas.transversal.handler.exception;


import uy.com.demente.ideas.transversal.handler.response.ExceptionDetails;

public class FileNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    private ExceptionDetails exceptionDetails;

    public FileNotFoundException() {
        super();
    }

    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNotFoundException(ExceptionDetails clientResponse, Throwable cause) {
        super(cause);
        this.exceptionDetails = exceptionDetails;
    }

    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(Throwable cause) {
        super(cause);
    }


    public ExceptionDetails getExceptionDetails() {
        return exceptionDetails;
    }

    public void setExceptionDetails(ExceptionDetails exceptionDetails) {
        this.exceptionDetails = exceptionDetails;
    }
}