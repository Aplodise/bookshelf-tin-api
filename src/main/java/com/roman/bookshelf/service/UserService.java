package com.roman.bookshelf.service;

import com.roman.bookshelf.domain.user.CredentialsDto;
import com.roman.bookshelf.domain.user.SignupDto;
import com.roman.bookshelf.domain.user.UserDto;
import com.roman.bookshelf.persistance.model.Role;
import com.roman.bookshelf.persistance.model.User;
import com.roman.bookshelf.persistance.repository.UserRepository;
import com.roman.bookshelf.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto login(CredentialsDto credentialsDto){
        User user = userRepository.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())){
            return userMapper.toDto(user);
        }
        throw new IllegalArgumentException("Invalid password");
    }
    public UserDto register(SignupDto userDto){
        userRepository.findByLogin(userDto.getLogin())
                .ifPresent(u -> {throw new IllegalArgumentException("User already exists");});

        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));
        return userMapper.toDto(userRepository.save(user));
    }
    public UserDto findByLogin(String login){
        return userRepository.findByLogin(login)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
