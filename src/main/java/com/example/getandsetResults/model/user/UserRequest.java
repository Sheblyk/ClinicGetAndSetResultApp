package com.example.getandsetResults.model.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UserRequest {
    @NotBlank(message = "surname is mandatory")
    private String surname;

    @NotBlank(message = "name is mandatory")
    private String name;

    @Min(value = 1, message = "Age should not be less than 1")
    @Max(value = 100, message = "Age should not be more than 100")
    private Integer age;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserRequest(@NotBlank(message = "surname is mandatory") String surname, @NotBlank(message = "name is mandatory") String name,
                       @Min(value = 1, message = "Age should not be less than 1")
                       @Max(value = 100, message = "Age should not be more than 100") Integer age) {
        this.surname = surname;
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserRequest that = (UserRequest) o;

        return new EqualsBuilder()
                .append(surname, that.surname)
                .append(name, that.name)
                .append(age, that.age)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(surname)
                .append(name)
                .append(age)
                .toHashCode();
    }
}
