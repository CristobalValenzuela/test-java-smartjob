package cl.smartjob.api.microservice.application.services;

import cl.smartjob.api.microservice.adapters.http.dto.UserDTO;
import cl.smartjob.api.microservice.application.conf.ValidationRegExp;
import cl.smartjob.api.microservice.application.ports.in.CreateUserUseCase;
import cl.smartjob.api.microservice.application.ports.in.DeleteUserUseCase;
import cl.smartjob.api.microservice.application.ports.in.GetUserUseCase;
import cl.smartjob.api.microservice.application.ports.in.ListUsersUseCase;
import cl.smartjob.api.microservice.application.ports.in.UpdateUserUseCase;
import cl.smartjob.api.microservice.application.ports.out.DeleteUserPort;
import cl.smartjob.api.microservice.application.ports.out.GetUserPort;
import cl.smartjob.api.microservice.application.ports.out.ListUsersPort;
import cl.smartjob.api.microservice.application.ports.out.SaveUserPort;
import cl.smartjob.api.microservice.application.ports.out.UpdateUserPort;
import cl.smartjob.api.microservice.domain.exception.ValidationException;
import cl.smartjob.api.microservice.domain.model.UserRespondeDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase, ListUsersUseCase, GetUserUseCase,
    DeleteUserUseCase, UpdateUserUseCase {

  private final SaveUserPort saveUserPort;
  private final ListUsersPort listUsersPort;
  private final GetUserPort getUserPort;
  private final UpdateUserPort updateUserPort;
  private final DeleteUserPort deleteUserPort;
  private final ValidationRegExp validationRegExp;
  private final JwtTokenService jwtTokenService;

  @Override
  public UserRespondeDTO createUser(UserDTO userDTO) {
    Map<String,String> errors = new HashMap<>();
    if(!userDTO.getEmail().matches(Optional.ofNullable(validationRegExp.getEmailRegexValue()).orElse(".*"))){
      errors.put("email", "Email no valido");
    }
    if(!userDTO.getPassword().matches(Optional.ofNullable(validationRegExp.getPasswordRegexValue()).orElse(".*"))){
      errors.put("password", "Password no valido");
    }

    if(!errors.isEmpty()) {
      throw new ValidationException(errors);
    }

    String token = jwtTokenService.generateToken(userDTO.getName());
    return saveUserPort.createUser(userDTO, token);
  }

  @Override
  public List<UserRespondeDTO> listUsers() {
    return listUsersPort.listUsers();
  }

  @Override
  public Boolean deleteUser(String uuid) {
    return deleteUserPort.deleteUser(uuid);
  }

  @Override
  public UserRespondeDTO getUser(String uuid) {
    return getUserPort.getUser(uuid);
  }

  @Override
  public UserRespondeDTO updateUser(String uuid, UserDTO userDTO) {
    return updateUserPort.updateUser(uuid, userDTO);
  }
}
