package bdd.demo.ecommerce.customer;

import bdd.demo.ecommerce.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.time.LocalDate;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class Customer extends BaseEntity<Long> {
    @Schema(description = "First name", example = "Jack")
    private String firstName;
    @Schema(description = "Last name", example = "Jones")
    private String lastName;
    @Schema(description = "Date of birth", example = "1970-01-01")
    LocalDate dateOfBirth;
    @Schema(description = "Gender", example = "M")
    String gender;
}
