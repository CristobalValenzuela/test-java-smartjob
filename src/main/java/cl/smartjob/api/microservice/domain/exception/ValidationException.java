package cl.smartjob.api.microservice.domain.exception;

import java.util.Map;
import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException{

  private Map<String,String> errors;
  public ValidationException(Map<String,String> errors){
    super("Error de validación");
    this.errors = errors;
  }
}
