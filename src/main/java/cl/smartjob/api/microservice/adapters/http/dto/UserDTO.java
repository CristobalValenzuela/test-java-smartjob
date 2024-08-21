package cl.smartjob.api.microservice.adapters.http.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
