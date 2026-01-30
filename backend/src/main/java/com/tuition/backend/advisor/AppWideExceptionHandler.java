package com.tuition.backend.advisor;

import com.tuition.backend.exception.AlreadyExistsException;
import com.tuition.backend.exception.NotFoundException;
import com.tuition.backend.util.StandardResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(
                new StandardResponse(400, "Validation Error", errors),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<StandardResponse> handleAlreadyExistsException(AlreadyExistsException e) {
        return new ResponseEntity<>(
                new StandardResponse(409, "Duplicate Entry", e.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(
                new StandardResponse(
                        400, "Bad Request", "Request body is missing or invalid JSON format."
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(
                new StandardResponse(404, "Error", e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String error = "Invalid parameter: " + e.getName() + " should be of type " + e.getRequiredType()
                .getSimpleName();
        return new ResponseEntity<>(
                new StandardResponse(400, "Invalid ID Format", error),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardResponse> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(
                new StandardResponse(400, "Validation Error", e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<StandardResponse> handleHandlerMethodValidationException(HandlerMethodValidationException e) {

        return new ResponseEntity<>(
                new StandardResponse(400, "Validation Error", e.toString().trim()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<StandardResponse> handleNoResourceFoundException(NoResourceFoundException e) {
        return new ResponseEntity<>(
                new StandardResponse(404, "Endpoint Not Found",
                        "The requested path was not found. Did you forget the ID?"),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(RuntimeException e) {
        return new ResponseEntity<>(
                new StandardResponse(404, "Error", e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}