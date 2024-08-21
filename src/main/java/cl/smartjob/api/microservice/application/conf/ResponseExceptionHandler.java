package cl.smartjob.api.microservice.application.conf;

import cl.smartjob.api.microservice.adapters.http.dto.ApiRespondeDTO;
import cl.smartjob.api.microservice.domain.exception.NotDataFoundException;
import cl.smartjob.api.microservice.domain.exception.ValidationException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage())
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiRespondeDTO.builder().errors(errors).mensaje("Error de validación").build());
  }
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<Object> handleValidationExceptions(ValidationException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiRespondeDTO.builder().errors(ex.getErrors()).mensaje(ex.getMessage()).build());
  }
  @ExceptionHandler(value = {NotDataFoundException.class})
  public ResponseEntity<Object> handleNotDataFoundException(NotDataFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiRespondeDTO.builder().mensaje(ex.getMessage()).build());
  }
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiRespondeDTO.builder().mensaje("Correo ya registrado").build());
  }
  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleException(Exception ex) {
    log.error(ex.getMessage(), ex);
    return ResponseEntity.internalServerError().build();
  }
}
