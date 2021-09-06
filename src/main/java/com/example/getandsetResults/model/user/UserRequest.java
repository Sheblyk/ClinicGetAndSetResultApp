package com.example.getandsetResults.model.user;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public record UserRequest(@NotBlank(message = "surname is mandatory") String surname,
                          @NotBlank(message = "name is mandatory") String name,
                          @Min(value = 1, message = "Age should not be less than 1")
                           @Max(value = 100, message = "Age should not be more than 100") Integer age) {
}
