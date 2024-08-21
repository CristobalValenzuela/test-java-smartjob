package cl.smartjob.api.microservice.application.ports.out;

import cl.smartjob.api.microservice.domain.model.UserRespondeDTO;
import java.util.List;

public interface ListUsersPort {

  List<UserRespondeDTO> listUsers();
}
