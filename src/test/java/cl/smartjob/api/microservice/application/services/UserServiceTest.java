package cl.smartjob.api.microservice.application.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import cl.smartjob.api.microservice.adapters.http.dto.PhoneDTO;
import cl.smartjob.api.microservice.adapters.http.dto.UserDTO;
import cl.smartjob.api.microservice.adapters.persistence.UserMapper;
import cl.smartjob.api.microservice.adapters.persistence.UserMapperImpl;
import cl.smartjob.api.microservice.adapters.persistence.entities.UserEntity;
import cl.smartjob.api.microservice.application.conf.ValidationRegExp;
import cl.smartjob.api.microservice.application.ports.out.DeleteUserPort;
import cl.smartjob.api.microservice.application.ports.out.GetUserPort;
import cl.smartjob.api.microservice.application.ports.out.ListUsersPort;
import cl.smartjob.api.microservice.application.ports.out.SaveUserPort;
import cl.smartjob.api.microservice.application.ports.out.UpdateUserPort;
import cl.smartjob.api.microservice.domain.exception.ValidationException;
import cl.smartjob.api.microservice.domain.model.UserRespondeDTO;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  SaveUserPort saveUserPort;
  @Mock
  ListUsersPort listUsersPort;
  @Mock
  GetUserPort getUserPort;
  @Mock
  UpdateUserPort updateUserPort;
  @Mock
  DeleteUserPort deleteUserPort;
  @Mock
  ValidationRegExp validationRegExp;
  @Mock
  JwtTokenService jwtTokenService;

  @InjectMocks
  UserService userService;

  UserMapper userMapper;

  @BeforeEach
  void setUp() {
    userMapper = new UserMapperImpl();
  }

  @Test
  void createUser() {
    UserRespondeDTO userRespondeDTO = getUserRespondeDTO();
    given(saveUserPort.createUser(any(), any()))
        .willReturn(userRespondeDTO);
    UserRespondeDTO userRespondeDTO2 = userService.createUser(getUserDTO());

    assertThat(userRespondeDTO2.getName()).isEqualTo(userRespondeDTO.getName());
    assertThat(userRespondeDTO2.getEmail()).isEqualTo(userRespondeDTO.getEmail());
    assertThat(userRespondeDTO2.getPassword()).isEqualTo(userRespondeDTO.getPassword());
    assertThat(userRespondeDTO2.getPhones()).isNotEmpty();
  }

  @Test
  void createUserEmailNoValid() {
    given(validationRegExp.getEmailRegexValue())
        .willReturn("valid@email.cl");
    ValidationException exception = Assertions.assertThrows(
        ValidationException.class,
        () -> userService.createUser(getUserDTO())
    );
    assertThat(exception.getErrors()).isNotEmpty();
  }

  @Test
  void createUserPasswordNoValid() {
    given(validationRegExp.getPasswordRegexValue())
        .willReturn("vvalidPassword");
    ValidationException exception = Assertions.assertThrows(
        ValidationException.class,
        () -> userService.createUser(getUserDTO())
    );
    assertThat(exception.getErrors()).isNotEmpty();
  }

  @Test
  void listUsers() {
    UserRespondeDTO userRespondeDTO = getUserRespondeDTO();
    given(listUsersPort.listUsers())
        .willReturn(List.of(userRespondeDTO));

    List<UserRespondeDTO> users = userService.listUsers();

    assertThat(users).isNotEmpty();
  }

  @Test
  void deleteUser() {
    given(deleteUserPort.deleteUser(any()))
        .willReturn(true);
    Boolean result = userService.deleteUser(UUID.randomUUID().toString());
    assertThat(result).isTrue();
  }

  @Test
  void getUser() {
    UserRespondeDTO userRespondeDTO = getUserRespondeDTO();
    given(getUserPort.getUser(any()))
        .willReturn(userRespondeDTO);
    UserRespondeDTO userRespondeDTO2 = userService.getUser(UUID.randomUUID().toString());

    assertThat(userRespondeDTO2.getName()).isEqualTo(userRespondeDTO.getName());
    assertThat(userRespondeDTO2.getEmail()).isEqualTo(userRespondeDTO.getEmail());
    assertThat(userRespondeDTO2.getPassword()).isEqualTo(userRespondeDTO.getPassword());
    assertThat(userRespondeDTO2.getPhones()).isNotEmpty();
  }

  @Test
  void updateUser() {
    UserRespondeDTO userRespondeDTO = getUserRespondeDTO();
    given(updateUserPort.updateUser(any(), any()))
        .willReturn(userRespondeDTO);
    UserRespondeDTO userRespondeDTO2 = userService.updateUser(UUID.randomUUID().toString(), getUserDTO());

    assertThat(userRespondeDTO2.getName()).isEqualTo(userRespondeDTO.getName());
    assertThat(userRespondeDTO2.getEmail()).isEqualTo(userRespondeDTO.getEmail());
    assertThat(userRespondeDTO2.getPassword()).isEqualTo(userRespondeDTO.getPassword());
    assertThat(userRespondeDTO2.getPhones()).isNotEmpty();
  }

  private UserDTO getUserDTO(){
    PhoneDTO phoneDTO = PhoneDTO.builder()
        .cityCode(2)
        .countryCode(56)
        .number(987654231)
        .build();
    return UserDTO.builder()
        .email("prueba@pl.cl")
        .name("Prueba")
        .phones(List.of(phoneDTO))
        .password("password")
        .build();
  }

  private UserRespondeDTO getUserRespondeDTO(){
    UserEntity userEntity = userMapper.toEntity(getUserDTO());
    userEntity.setId(UUID.randomUUID());
    return userMapper.toResponseDTO(userEntity);
  }
}