package cl.smartjob.api.microservice.adapters.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import cl.smartjob.api.microservice.BaseTest;
import cl.smartjob.api.microservice.adapters.persistence.entities.UserEntity;
import cl.smartjob.api.microservice.domain.exception.NotDataFoundException;
import cl.smartjob.api.microservice.domain.exception.ValidationException;
import cl.smartjob.api.microservice.domain.model.UserRespondeDTO;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserAdapterTest extends BaseTest {

  @Mock
  UserRepository userRepository;
  UserMapper userMapper;
  UserAdapter userAdapter;

  @BeforeEach
  public void setup(){
    userMapper = new UserMapperImpl();
    userAdapter = new UserAdapter(userRepository, userMapper);
  }

  @Test
  void createUser() {
    UserEntity userEntity = getUserEntity();
    given(userRepository.save(any()))
        .willReturn(userEntity);
    UserRespondeDTO expected = userMapper.toResponseDTO(userEntity);
    UserRespondeDTO result = userAdapter.createUser(getUserDTO(), "token");

    assertThat(result.getName()).isEqualTo(expected.getName());
    assertThat(result.getEmail()).isEqualTo(expected.getEmail());
    assertThat(result.getPassword()).isEqualTo(expected.getPassword());
    assertThat(result.getPhones()).isNotEmpty();

  }

  @Test
  void listUsers() {
    UserEntity userEntity = getUserEntity();
    given(userRepository.findAll())
        .willReturn(List.of(userEntity));

    List<UserRespondeDTO> users = userAdapter.listUsers();

    assertThat(users).isNotEmpty();
  }

  @Test
  void listUsersNotFound() {
    given(userRepository.findAll())
        .willReturn(Collections.emptyList());

    NotDataFoundException exception = assertThrows(
        NotDataFoundException.class,
        () -> userAdapter.listUsers()
    );
    assertThat(exception).isNotNull();
    assertThat(exception.getMessage()).isEqualTo("Sin usuarios registrados");
  }

  @Test
  void deleteUser() {
    UserEntity userEntity = getUserEntity();
    given(userRepository.findById(any()))
        .willReturn(Optional.of(userEntity));
    given(userRepository.save(any()))
        .willReturn(userEntity);

    Boolean delete = userAdapter.deleteUser(UUID.randomUUID().toString());

    assertThat(delete).isTrue();
  }

  @Test
  void deleteUserNotFound() {
    given(userRepository.findById(any()))
        .willReturn(Optional.empty());

    NotDataFoundException exception = assertThrows(
        NotDataFoundException.class,
        () ->userAdapter.deleteUser(UUID.randomUUID().toString())
    );
    assertThat(exception).isNotNull();
    assertThat(exception.getMessage()).isEqualTo("Usuario no encontrado");
  }

  @Test
  void getUser() {
    UserEntity userEntity = getUserEntity();
    given(userRepository.findById(any()))
        .willReturn(Optional.of(userEntity));

    UserRespondeDTO expected = userMapper.toResponseDTO(userEntity);
    UserRespondeDTO result = userAdapter.getUser(UUID.randomUUID().toString());

    assertThat(result.getName()).isEqualTo(expected.getName());
    assertThat(result.getEmail()).isEqualTo(expected.getEmail());
    assertThat(result.getPassword()).isEqualTo(expected.getPassword());
    assertThat(result.getPhones()).isNotEmpty();
  }

  @Test
  void getUserNotFound() {
    given(userRepository.findById(any()))
        .willReturn(Optional.empty());

    NotDataFoundException exception = assertThrows(
        NotDataFoundException.class,
        () ->userAdapter.getUser(UUID.randomUUID().toString())
    );
    assertThat(exception).isNotNull();
    assertThat(exception.getMessage()).isEqualTo("Usuario no encontrado");
  }

  @Test
  void updateUser() {
    UserEntity userEntity = getUserEntity();
    given(userRepository.findById(any()))
        .willReturn(Optional.of(userEntity));
    given(userRepository.save(any()))
        .willReturn(userEntity);

    UserRespondeDTO expected = userMapper.toResponseDTO(userEntity);
    UserRespondeDTO result = userAdapter.updateUser(UUID.randomUUID().toString(), getUserDTO());

    assertThat(result.getName()).isEqualTo(expected.getName());
    assertThat(result.getEmail()).isEqualTo(expected.getEmail());
    assertThat(result.getPassword()).isEqualTo(expected.getPassword());
    assertThat(result.getPhones()).isNotEmpty();
  }

  @Test
  void updateUserNotFound() {
    given(userRepository.findById(any()))
        .willReturn(Optional.empty());

    NotDataFoundException exception = assertThrows(
        NotDataFoundException.class,
        () ->userAdapter.updateUser(UUID.randomUUID().toString(), getUserDTO())
    );
    assertThat(exception).isNotNull();
    assertThat(exception.getMessage()).isEqualTo("Usuario no encontrado");
  }
}