package cl.smartjob.api.microservice.adapters.persistence;

import cl.smartjob.api.microservice.adapters.persistence.entities.UserEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
