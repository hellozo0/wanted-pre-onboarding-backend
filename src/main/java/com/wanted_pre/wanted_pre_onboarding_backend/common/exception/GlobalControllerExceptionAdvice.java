package com.wanted_pre.wanted_pre_onboarding_backend.common.exception;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;

import com.wanted_pre.wanted_pre_onboarding_backend.common.dto.ErrorResponse;
import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model.BadRequestException;
import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model.ConflictException;
import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model.NotFoundException;
import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model.NotFoundUserException;
import com.wanted_pre.wanted_pre_onboarding_backend.common.exception.model.UnAuthorizedException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

import static com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode.INTERNAL_SERVER_EXCEPTION;
import static com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode.INVALID_EMPTY_TYPE_EXCEPTION;
import static com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode.INVALID_VALUE_TYPE_EXCEPTION;
import static com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode.METHOD_NOT_ALLOWED_EXCEPTION;
import static com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode.NOT_FOUND_RESOURCE_EXCEPTION;
import static com.wanted_pre.wanted_pre_onboarding_backend.common.exception.enums.ErrorCode.VALIDATION_REQUEST_MISSING_EXCEPTION;


@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionAdvice {
    /**
     * 400 Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    protected ErrorResponse handleBadRequestException(final BadRequestException e) {
        return ErrorResponse.error(e.getErrorCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ErrorResponse handleTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        return ErrorResponse.error(INVALID_VALUE_TYPE_EXCEPTION);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ErrorResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        String errorMessages = e.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return ErrorResponse.badRequestError(errorMessages);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    protected ErrorResponse handleConstraintViolationException(final ConstraintViolationException e) {
        return ErrorResponse.badRequestError(INVALID_EMPTY_TYPE_EXCEPTION.getMessage() + " " + e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValueInstantiationException.class)
    protected ErrorResponse handleValueInstantiationException(final ValueInstantiationException e) {
        return ErrorResponse.badRequestError(VALIDATION_REQUEST_MISSING_EXCEPTION.getMessage() + " " + e.getMessage());
    }

    /**
     * 401 UnAuthorization
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorizedException.class)
    protected ErrorResponse handleUnAuthorizedException(final UnAuthorizedException e) {
        return ErrorResponse.error(e.getErrorCode());
    }

    /**
     * 404 Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    protected ErrorResponse handleNotFoundException(final NotFoundException e) {
        return ErrorResponse.error(e.getErrorCode());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundUserException.class)
    protected ErrorResponse handleNotFoundUserException(final NotFoundUserException e) {
        return ErrorResponse.error(e.getErrorCode());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    protected ErrorResponse handleNoResourceFoundException(final NoResourceFoundException e) {
        return ErrorResponse.error(NOT_FOUND_RESOURCE_EXCEPTION);
    }

    /**
     * 405 Method Not Allowed
     * 지원하지 않은 HTTP method 호출 할 경우 발생하는 Exception
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ErrorResponse handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        return ErrorResponse.error(METHOD_NOT_ALLOWED_EXCEPTION);
    }

    /**
     * 409 Conflict
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    protected ErrorResponse handleConflictException(final ConflictException e) {
        return ErrorResponse.error(e.getErrorCode());
    }

    /**
     * 500 Internal Server
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ErrorResponse handleException(final Exception error) {
        log.error(error.getMessage(), error);
        return ErrorResponse.error(INTERNAL_SERVER_EXCEPTION);
    }
}
