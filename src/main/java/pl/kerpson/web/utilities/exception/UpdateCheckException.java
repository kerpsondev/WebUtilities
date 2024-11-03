package pl.kerpson.web.utilities.exception;

public class UpdateCheckException extends RuntimeException {

  public UpdateCheckException(Throwable cause) {
    super("Failed to check for updates", cause);
  }
}
