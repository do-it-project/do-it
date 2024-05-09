package br.com.project.backend.model;

import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "S")
public class Student extends User {

}
