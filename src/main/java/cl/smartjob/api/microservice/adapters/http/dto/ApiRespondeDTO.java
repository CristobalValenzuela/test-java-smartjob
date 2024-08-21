package cl.smartjob.api.microservice.adapters.http.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@Builder
public class ApiRespondeDTO {

  private String mensaje;
  private Object data;
  private Object errors;
}
