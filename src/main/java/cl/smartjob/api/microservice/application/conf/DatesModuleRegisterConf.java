package cl.smartjob.api.microservice.application.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatesModuleRegisterConf {

  @Autowired
  private ObjectMapper mapper;

  @PostConstruct
  private void init(){
    mapper.findAndRegisterModules();
  }
}
