package org.marcinski.chickenHouse.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.UUID;

@Data
public class UserDto {

    private Long id;

    @Email(message = "Wpisz email w poprawnej formie!")
    @NotEmpty(message = "Wpisz email!")
    private String email;

    @NotEmpty(message = "Wpisz hasło!")
    @Length(min = 5, message = "Hasło musi mieć przynajmniej 5 znaków!")
    private String password;

    @NotEmpty(message ="Wpisz swoje imię!")
    private String name;

    private UUID uuid;

    private boolean active;

    private Set<RoleDto> roleDtos;

    private Set<ChickenHouseDto> chickenHouseDtos;
}
