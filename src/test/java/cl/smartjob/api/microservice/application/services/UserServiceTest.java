package cl.smartjob.api.microservice.application.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import cl.smartjob.api.microservice.BaseTest;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest extends BaseTest {

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

  @Test
  void createUser() {
    UserRespondeDTO expected = getUserRespondeDTO();
    given(saveUserPort.createUser(any(), any()))
        .willReturn(expected);
    UserRespondeDTO result = userService.createUser(getUserDTO());

    assertThat(result.getName()).isEqualTo(expected.getName());
    assertThat(result.getEmail()).isEqualTo(expected.getEmail());
    assertThat(result.getPassword()).isEqualTo(expected.getPassword());
    assertThat(result.getPhones()).isNotEmpty();
  }

  @Test
  void createUserEmailNoValid() {
    given(validationRegExp.getEmailRegexValue())
        .willReturn("valid@email.cl");
    ValidationException exception = assertThrows(
        ValidationException.class,
        () -> userService.createUser(getUserDTO())
    );
    assertThat(exception.getErrors()).isNotEmpty();
  }

  @Test
  void createUserPasswordNoValid() {
    given(validationRegExp.getPasswordRegexValue())
        .willReturn("vvalidPassword");
    ValidationException exception = assertThrows(
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
}