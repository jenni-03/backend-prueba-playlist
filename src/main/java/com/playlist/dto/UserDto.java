package com.playlist.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String id;

    private String email;

    private String name;

    private String token;

}
