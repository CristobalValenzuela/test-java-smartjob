package cl.smartjob.api.microservice.adapters.http;

import cl.smartjob.api.microservice.adapters.http.dto.ApiRespondeDTO;
import cl.smartjob.api.microservice.adapters.http.dto.UserDTO;
import cl.smartjob.api.microservice.application.ports.in.CreateUserUseCase;
import cl.smartjob.api.microservice.application.ports.in.DeleteUserUseCase;
import cl.smartjob.api.microservice.application.ports.in.GetUserUseCase;
import cl.smartjob.api.microservice.application.ports.in.ListUsersUseCase;
import cl.smartjob.api.microservice.application.ports.in.UpdateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final CreateUserUseCase createUserUseCase;
  private final ListUsersUseCase listUsersUseCase;
  private final GetUserUseCase getUserUseCase;
  private final UpdateUserUseCase updateUserUseCase;
  private final DeleteUserUseCase deleteUserUseCase;

  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> listUsers(){
    return ResponseEntity.ok(ApiRespondeDTO.builder().data(listUsersUseCase.listUsers()).build());
  }

  @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getUser(@PathVariable(value = "uuid") String uuid){
    return ResponseEntity.ok(ApiRespondeDTO.builder().data(getUserUseCase.getUser(uuid)).build());
  }

  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO userDTO){
    return ResponseEntity.ok(ApiRespondeDTO.builder().data(createUserUseCase.createUser(userDTO)).mensaje("usuario creado").build());
  }

  @PutMapping(value = "/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> updateUser(@PathVariable(value = "uuid") String uuid, @Valid @RequestBody UserDTO userDTO){
    return ResponseEntity.ok(ApiRespondeDTO.builder().data(updateUserUseCase.updateUser(uuid, userDTO)).mensaje("usuario actualizado").build());
  }

  @DeleteMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> deleteUser(@PathVariable(value = "uuid") String uuid){
    return ResponseEntity.ok(ApiRespondeDTO.builder().data(deleteUserUseCase.deleteUser(uuid)).mensaje("usuario eliminado").build());
  }
}
