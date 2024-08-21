package cl.smartjob.api.microservice.adapters.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PhoneDTO {

  private Integer number;
  @JsonProperty(value = "citycode")
  private Integer cityCode;
  @JsonProperty(value = "countrycode")
  private Integer countryCode;
}
