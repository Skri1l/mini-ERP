package com.skr1l.minierp.security;

import com.skr1l.minierp.dto.RegisterRequestDto;

public interface AuthService {

    Long register(RegisterRequestDto registerDto);

}
