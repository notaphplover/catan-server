package io.github.notaphplover.catanserver.common.adapter.api.exceptionHandler;

import io.github.notaphplover.catanserver.common.adapter.api.exception.EntityNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

class ErrorResponse {
  private String msg;

  public ErrorResponse(String msg) {
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }
}

@ControllerAdvice(annotations = Controller.class)
public class ControllerExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
    ErrorResponse errorResponse = new ErrorResponse(e.getMessage());

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {

    ErrorResponse errorResponse = new ErrorResponse(prettifyBindingResult(e.getBindingResult()));

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception e) {

    ErrorResponse errorResponse = new ErrorResponse(e.toString());

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private String prettifyBindingResult(BindingResult bindingResult) {
    StringBuilder sb = new StringBuilder();

    sb.append('[').append(bindingResult.getObjectName()).append(']').append(System.lineSeparator());

    List<ObjectError> globalErrors = bindingResult.getGlobalErrors();

    for (ObjectError error : globalErrors) {
      sb.append(error.toString()).append(System.lineSeparator());
    }

    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
    for (FieldError error : fieldErrors) {
      sb.append(error.getField())
          .append(": ")
          .append(error.getDefaultMessage())
          .append(System.lineSeparator());
    }

    return sb.toString();
  }
}
