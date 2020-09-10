package uy.com.demente.ideas.transversal.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uy.com.demente.ideas.transversal.handler.exception.*;
import uy.com.demente.ideas.transversal.handler.response.ErrorMessage;
import uy.com.demente.ideas.transversal.handler.response.ExceptionDetails;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {BussinesServiceException.class})
    public ResponseEntity<ErrorMessage> handlerException(BussinesServiceException ex) {
        return buildResponseEntityByException(HttpStatus.BAD_REQUEST, ex.getExceptionDetails());
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<ErrorMessage> handlerException(UnauthorizedException ex) {
        return buildResponseEntityByException(HttpStatus.UNAUTHORIZED, ex.getExceptionDetails());
    }

    @ExceptionHandler(value = {ForbiddenException.class})
    public ResponseEntity<ErrorMessage> handlerException(ForbiddenException ex) {
        return buildResponseEntityByException(HttpStatus.FORBIDDEN, ex.getExceptionDetails());
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorMessage> handlerException(ResourceNotFoundException ex) {
        return buildResponseEntityByException(HttpStatus.NOT_FOUND, ex.getExceptionDetails());
    }

    @ExceptionHandler(value = {InternalServerErrorException.class})
    public ResponseEntity<ErrorMessage> handlerException(InternalServerErrorException ex) {
        return buildResponseEntityByException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getExceptionDetails());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handlerException(MethodArgumentNotValidException ex) {

        String messageErrors = ex.getBindingResult()
                .getAllErrors().stream().map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .cause(messageErrors)
                .build();

        ResponseEntity<ErrorMessage> responseEntity =
                buildResponseEntityByException(HttpStatus.BAD_REQUEST, exceptionDetails);
        return responseEntity;
    }

    private ResponseEntity<ErrorMessage> buildResponseEntityByException(HttpStatus httpStatus,
                                                                        ExceptionDetails exceptionDetails) {
        ErrorMessage errorMessage = getErrorMessage(exceptionDetails, httpStatus);
        ResponseEntity<ErrorMessage> responseEntity =
                new ResponseEntity<>(errorMessage, new HttpHeaders(), httpStatus);
        return responseEntity;
    }

    private ErrorMessage getErrorMessage(ExceptionDetails exceptionDetails, HttpStatus httpStatus) {

        ErrorMessage errorMessage = ErrorMessage.builder()
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(exceptionDetails.getCause())
                .timestamp(LocalDateTime.now())
                .build();
        return errorMessage;
    }
}