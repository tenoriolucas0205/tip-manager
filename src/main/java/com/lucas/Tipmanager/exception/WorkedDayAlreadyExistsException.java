package com.lucas.Tipmanager.exception;

public class WorkedDayAlreadyExistsException extends RuntimeException {
  public WorkedDayAlreadyExistsException(String message) {
    super(message);
  }
}
