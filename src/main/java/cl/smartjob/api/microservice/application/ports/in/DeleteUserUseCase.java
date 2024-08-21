package cl.smartjob.api.microservice.application.ports.in;

public interface DeleteUserUseCase {

  Boolean deleteUser(String uuid);
}
