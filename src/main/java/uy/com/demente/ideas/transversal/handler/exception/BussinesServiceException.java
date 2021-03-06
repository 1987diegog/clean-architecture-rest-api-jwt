package uy.com.demente.ideas.transversal.handler.exception;


import uy.com.demente.ideas.transversal.handler.response.ExceptionDetails;

public class BussinesServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    private ExceptionDetails exceptionDetails;

    public BussinesServiceException() {
        super();
    }

    public BussinesServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BussinesServiceException(ExceptionDetails exceptionDetails, Throwable cause) {
        super(cause);
        this.exceptionDetails = exceptionDetails;
    }

    public BussinesServiceException(String message) {
        super(message);
    }

    public BussinesServiceException(Throwable cause) {
        super(cause);
    }

    public ExceptionDetails getExceptionDetails() {
        return exceptionDetails;
    }

    public void setExceptionDetails(ExceptionDetails exceptionDetails) {
        this.exceptionDetails = exceptionDetails;
    }
}
