package cl.smartjob.api.microservice.application.ports.in;

import cl.smartjob.api.microservice.adapters.http.dto.UserDTO;
import cl.smartjob.api.microservice.domain.model.UserRespondeDTO;

public interface UpdateUserUseCase {

  UserRespondeDTO updateUser(String uuid, UserDTO userDTO);
}
