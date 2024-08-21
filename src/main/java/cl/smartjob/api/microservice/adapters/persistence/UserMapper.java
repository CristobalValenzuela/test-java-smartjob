package cl.smartjob.api.microservice.adapters.persistence;

import cl.smartjob.api.microservice.adapters.http.dto.PhoneDTO;
import cl.smartjob.api.microservice.adapters.http.dto.UserDTO;
import cl.smartjob.api.microservice.adapters.persistence.entities.PhoneEntity;
import cl.smartjob.api.microservice.adapters.persistence.entities.UserEntity;
import cl.smartjob.api.microservice.domain.model.UserRespondeDTO;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring"
)
public interface UserMapper {

  UserEntity toEntity(UserDTO userDTO);
  @Mapping(target = "id", expression = "java(userEntity.getId().toString())")
  UserRespondeDTO toResponseDTO(UserEntity userEntity);
}
