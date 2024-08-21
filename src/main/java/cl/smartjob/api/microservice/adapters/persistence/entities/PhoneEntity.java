package cl.smartjob.api.microservice.adapters.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
public class PhoneEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "phone_id")
  private UUID id;
  private Integer number;
  private Integer cityCode;
  private Integer countryCode;
  @ManyToOne
  @JoinColumn(name="user_id", nullable = false, updatable = false)
  private UserEntity user;

}
