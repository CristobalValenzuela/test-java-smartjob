package cl.smartjob.api.microservice.application.ports.in;

import cl.smartjob.api.microservice.domain.model.UserRespondeDTO;

public interface GetUserUseCase {

  UserRespondeDTO getUser(String uuid);
}
