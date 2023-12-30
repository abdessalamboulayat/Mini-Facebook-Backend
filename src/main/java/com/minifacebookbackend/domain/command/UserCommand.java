package com.minifacebookbackend.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCommand {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String location;
    private String biography;
}
