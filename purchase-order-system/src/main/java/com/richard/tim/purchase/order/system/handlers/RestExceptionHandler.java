package com.richard.tim.purchase.order.system.handlers;


import com.google.common.collect.Lists;
import com.richard.tim.purchase.order.system.exceptions.DepartmentNotFoundException;
import com.richard.tim.purchase.order.system.model.dto.ApiErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAccessDeniedException(final Exception ex, final WebRequest request) {
        if (ex.getMessage().toLowerCase().indexOf("access is denied") > -1) {
            return new ResponseEntity<Object>("Unauthorized Access", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiErrorDTO> handleAll(final Exception ex,
                                                 final WebRequest request) {
        log.info(ex.getClass().getName());
        log.error("error", ex);

        final ApiErrorDTO apiErrorDTO = ApiErrorDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                .errors(Arrays.asList(ex.getLocalizedMessage()))
                .message("An Error Occurred.").build();
        return new ResponseEntity<>(apiErrorDTO, new HttpHeaders(),
                apiErrorDTO.getStatus());
    }

    @ExceptionHandler({JpaSystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorDTO> handleConstraintViolation(
            final JpaSystemException ex, final WebRequest request) {
        final Throwable cause = ex.getRootCause();
        if (cause instanceof ConstraintViolationException) {
            final Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause)
                    .getConstraintViolations();

            final List<String> errors = new ArrayList<>();
            for (final ConstraintViolation<?> violation : constraintViolations) {
                errors.add(violation.getPropertyPath() + ": "
                        + violation.getMessage());

                final ApiErrorDTO apiErrorDTO = ApiErrorDTO.builder().status(HttpStatus.BAD_REQUEST)
                        .errors(errors).message(ex.getLocalizedMessage()).build();
                return new ResponseEntity<>(apiErrorDTO, new HttpHeaders(),
                        apiErrorDTO.getStatus());
            }
        }

        throw ex;
    }

    @ExceptionHandler({DepartmentNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorDTO> handleDepartmentNotFound(final Exception ex,
                                                                final WebRequest request) {
        log.info(ex.getClass().getName());
        log.error("error", ex);

        final ApiErrorDTO apiErrorDTO = ApiErrorDTO.builder().status(HttpStatus.NOT_FOUND)
                .errors(Arrays.asList(ex.getLocalizedMessage()))
                .message("An Error Occurred.").build();
        return new ResponseEntity<>(apiErrorDTO, new HttpHeaders(),
                apiErrorDTO.getStatus());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDTO> handleValidationExceptions(
            final MethodArgumentNotValidException ex) {

        final List<String> errors = Lists.newArrayList();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            final String fieldName = ((FieldError) error).getField();
            final String errorMessage = error.getDefaultMessage();
            errors.add(String.format("Field Name: %s Error Message: %s", fieldName, errorMessage));
        });
        final ApiErrorDTO apiErrorDTO = ApiErrorDTO.builder().status(HttpStatus.NOT_FOUND)
                .errors(errors)
                .message("A validation error occurred.").build();

        return new ResponseEntity<>(apiErrorDTO, new HttpHeaders(),
                apiErrorDTO.getStatus());
    }
}
