package cl.smartjob.api.microservice.application.ports.in;

import cl.smartjob.api.microservice.domain.model.UserRespondeDTO;
import java.util.List;

public interface ListUsersUseCase {

  List<UserRespondeDTO> listUsers();

}
