package cl.smartjob.api.microservice.application.ports.out;

import cl.smartjob.api.microservice.adapters.http.dto.UserDTO;
import cl.smartjob.api.microservice.domain.model.UserRespondeDTO;

public interface SaveUserPort {

  UserRespondeDTO createUser(UserDTO userDTO, String token);
}
