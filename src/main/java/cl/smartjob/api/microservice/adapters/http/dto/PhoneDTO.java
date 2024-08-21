package cl.smartjob.api.microservice.adapters.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneDTO {

  private Integer number;
  @JsonProperty(value = "citycode")
  private Integer cityCode;
  @JsonProperty(value = "countrycode")
  private Integer countryCode;
}
