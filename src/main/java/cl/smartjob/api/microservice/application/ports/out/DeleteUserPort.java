package cl.smartjob.api.microservice.application.ports.out;

public interface DeleteUserPort {

  Boolean deleteUser(String uuid);
}
