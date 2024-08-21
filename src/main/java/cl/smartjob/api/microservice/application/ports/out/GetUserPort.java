package cl.smartjob.api.microservice.application.ports.out;

import cl.smartjob.api.microservice.domain.model.UserRespondeDTO;

public interface GetUserPort {

  UserRespondeDTO getUser(String uuid);
}
