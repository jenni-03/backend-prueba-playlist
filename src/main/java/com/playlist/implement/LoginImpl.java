package com.playlist.implement;

import com.playlist.controller.request.LoginRequest;
import com.playlist.controller.request.RegisterRequest;
import com.playlist.dto.User;
import com.playlist.dto.UserDto;
import com.playlist.exceptions.BadCredentialsException;
import com.playlist.mapper.LoginMapper;
import com.playlist.security.CustomUserDetailsService;
import com.playlist.security.JwtService;
import com.playlist.service.LoginService;
import com.playlist.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class LoginImpl implements LoginService {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final LoginMapper loginMapper;

    @Override
    public UserDto login(LoginRequest login)  {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(login.getUsername());

        boolean match = passwordEncoder.matches(login.getPassword(), userDetails.getPassword());
        if (!match) {
            throw new BadCredentialsException("playlist.message.user.bad.credentials");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userService.getUserByEmail(login.getUsername());

        String token = jwtService.getToken(userDetails);

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .token(token)
                .build();
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        User user = loginMapper.requestToDomain(registerRequest);

        userService.saveUser(user);

        return "Usuario registrado exitosamente";
    }
}
