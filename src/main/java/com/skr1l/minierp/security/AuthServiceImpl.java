package com.skr1l.minierp.security;

import com.skr1l.minierp.dto.RegisterRequestDto;
import com.skr1l.minierp.entity.User;
import com.skr1l.minierp.repository.UserRepository;
import com.skr1l.minierp.roles.AppRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Long register(RegisterRequestDto registerDto) {

        Objects.requireNonNull(registerDto, "register dto is null");

        String email = registerDto.email().trim().toLowerCase();

        if (userRepository.existsByEmail(email)){
            throw new EmailAlreadyExistsException("User with this email: " +
                    registerDto.email() + " already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        user.setAppRole(AppRole.USER);

        userRepository.save(user);
        return user.getId();
    }
}
