package cl.smartjob.api.microservice.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@Builder
public class UserRespondeDTO {

  private String id;
  private String name;
  private String email;
  private String password;
  private List<PhoneResponseDTO> phones;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime created;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime modified;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime lastLogin;
  private String token;
  private Boolean isActive;
}
