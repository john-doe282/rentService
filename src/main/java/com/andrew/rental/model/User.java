package com.andrew.rental.model;

import com.andrew.rental.UsersShort;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private UUID id;

    private String name;
    private String surname;
    private String email;
    private String login;

    private Role role;

    public static User fromUsersShort (UsersShort response) {
        return new UserBuilder().
                id(UUID.fromString(response.getId())).
                name(response.getName()).
                surname(response.getSurname()).
                email(response.getEmail()).
                login(response.getLogin()).
                role(Role.valueOf(response.getRole().toString())).
                build();
    }
}
