package io.xor.project.blogapi.exception;

import io.xor.project.blogapi.exception.error.ClientErrorDetails;
import io.xor.project.blogapi.exception.error.ErrorDetails;
import io.xor.project.blogapi.exception.error.InternalErrorDetails;
import io.xor.project.blogapi.exception.http_exceptions.BadRequestException;
import io.xor.project.blogapi.exception.http_exceptions.InternalServerException;
import io.xor.project.blogapi.exception.http_exceptions.NotFoundException;
import io.xor.project.blogapi.exception.http_exceptions.UnauthorizedException;
import io.xor.project.blogapi.exception.internal_exceptions.IE_PersistenceException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final HttpRequestHandlerAdapter httpRequestHandlerAdapter;

    public GlobalExceptionHandler(HttpRequestHandlerAdapter httpRequestHandlerAdapter) {
        this.httpRequestHandlerAdapter = httpRequestHandlerAdapter;
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails> handleBadRequestException(BadRequestException e, HttpServletRequest httpRequest) {
        String uri = getURI(httpRequest);
        String method = getMethod(httpRequest);
        ClientErrorDetails clientDetails = new ClientErrorDetails(uri, method, LocalDateTime.now(),e.getMessage());
        logger.error(e.getMessage());
        return new ResponseEntity<>(clientDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(NotFoundException e, HttpServletRequest httpRequest) {
        String uri = getURI(httpRequest);
        String method = getMethod(httpRequest);
        ClientErrorDetails clientDetails = new ClientErrorDetails(uri, method, LocalDateTime.now(),e.getMessage());

        logger.error(prefixErrorHttp(e.getMessage()));
        return new ResponseEntity<>(clientDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorDetails> handleInternalServerException(InternalServerException e, HttpServletRequest httpRequest) {
        String uri = getURI(httpRequest);
        String method = getMethod(httpRequest);
        ClientErrorDetails clientDetails = new ClientErrorDetails(uri, method, LocalDateTime.now(),e.getMessage());

        logger.error(prefixErrorHttp(e.getErrorDetail()));
        return new ResponseEntity<>(clientDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorDetails> handleUnauthorizedException(UnauthorizedException e, HttpServletRequest httpRequest) {
        String uri = getURI(httpRequest);
        String method = getMethod(httpRequest);
        ClientErrorDetails clientDetails = new ClientErrorDetails(uri, method, LocalDateTime.now(),e.getMessage());

        logger.error(prefixErrorHttp(e.getMessage()));
        return new ResponseEntity<>(clientDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IE_PersistenceException.class)
    public ResponseEntity<ErrorDetails> handleIePersistenceException(IE_PersistenceException e, HttpServletRequest httpRequest) {
        LocalDateTime time = LocalDateTime.now();
        InternalErrorDetails internalError = new InternalErrorDetails(time, e.getMessage());
        logger.error(prefixErrorInternal(internalError.getDetail()));

        String uri = getURI(httpRequest);
        String method = getMethod(httpRequest);
        ClientErrorDetails clientError = new ClientErrorDetails(uri, method, time, "Internal Server Error");
        return new ResponseEntity<>(clientError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getMethod(HttpServletRequest req) {
        return req.getMethod();
    }

    private String getURI(HttpServletRequest req) {
        return req.getRequestURI();
    }

    private String prefixErrorHttp(String e) {
        return String.format("HTTP-ERROR: %s", e);
    }

    private String prefixErrorInternal(String e) {
        return String.format("INTERNAL-ERROR: %s", e);
    }

}
