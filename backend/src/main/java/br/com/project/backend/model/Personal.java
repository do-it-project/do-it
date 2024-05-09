package br.com.project.backend.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "P")
public class Personal extends User {

}
