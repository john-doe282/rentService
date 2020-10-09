package com.andrew.rental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class User {
    private UUID id;

    private String name;
    private String surname;
    private String email;
    private String login;

    @Enumerated(EnumType.STRING)
    private Role role;
}
