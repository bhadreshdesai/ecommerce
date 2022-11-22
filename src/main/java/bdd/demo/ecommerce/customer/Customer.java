package bdd.demo.ecommerce.customer;

import bdd.demo.ecommerce.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class Customer extends BaseEntity<Long> {

    private String firstName;
    private String lastName;
    LocalDate dateOfBirth;
    String gender;
}
