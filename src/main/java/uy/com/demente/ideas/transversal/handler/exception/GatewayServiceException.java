package uy.com.demente.ideas.transversal.handler.exception;

import uy.com.demente.ideas.transversal.handler.response.ExceptionDetails;

public class GatewayServiceException extends Exception {

    private ExceptionDetails exceptionDetails;

    public GatewayServiceException() {
        super();
    }

    public GatewayServiceException(ExceptionDetails exceptionDetails, Throwable cause) {
        super(cause);
        this.exceptionDetails = exceptionDetails;
    }

    public GatewayServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatewayServiceException(String message) {
        super(message);
    }

    public GatewayServiceException(Throwable cause) {
        super(cause);
    }

    public ExceptionDetails getExceptionDetails() {
        return exceptionDetails;
    }

    public void setExceptionDetails(ExceptionDetails exceptionDetails) {
        this.exceptionDetails = exceptionDetails;
    }
}
