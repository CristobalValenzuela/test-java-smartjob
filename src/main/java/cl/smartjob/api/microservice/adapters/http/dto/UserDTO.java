package cl.smartjob.api.microservice.adapters.http.dto;

import cl.smartjob.api.microservice.application.conf.ValidationRegExp;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.Data;

@Data
public class UserDTO {
  @NotEmpty(message = "Nombre no puede estar vacío")
  private String name;
  @NotEmpty(message = "Email no puede estar vacío")
  private String email;
  @NotEmpty(message = "Password no puede estar vacío")
  private String password;
  @NotEmpty(message = "Al menos de haber un teléfono")
  private List<PhoneDTO> phones;
}
