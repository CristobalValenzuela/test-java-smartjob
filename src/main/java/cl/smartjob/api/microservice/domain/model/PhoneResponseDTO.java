package cl.smartjob.api.microservice.domain.model;

import lombok.Data;

@Data
public class PhoneResponseDTO {

  private Integer number;
  private Integer cityCode;
  private Integer countryCode;

}
