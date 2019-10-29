package com.ktc.camunda;

public class ShellException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ShellException(String message) {
    super(message);
  }

  public ShellException(Throwable cause) {
    super(cause);
  }

  public ShellException(String message, Throwable cause) {
    super(message, cause);
  }

}

