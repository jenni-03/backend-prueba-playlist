package com.playlist.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;

    private String email;

    private String password;

    private String name;

}
