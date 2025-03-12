package example.mvcdemo.controller;

import example.mvcdemo.model.ExceptionResponse;
import example.mvcdemo.service.ResourceNotFoundException;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( value = Exception.class)
    public ResponseEntity<Object> handleServerError(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage("server error");
        response.setStatusCode(status.value());
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @ExceptionHandler( value = ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage("resource not found");
        response.setStatusCode(status.value());
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @ExceptionHandler( value = {IllegalStateException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage("conflict");
        response.setStatusCode(status.value());
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                   @Nullable Object body,
                                                   HttpHeaders headers,
                                                   HttpStatusCode statusCode,
                                                   WebRequest request) {
        if (statusCode.is5xxServerError()) {
            logger.error(ex.getMessage(), ex);
        } else {
            logger.info(ex.getMessage(), ex);
        }
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }
}
