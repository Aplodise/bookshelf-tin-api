package com.roman.bookshelf.service.mapper;

import com.roman.bookshelf.domain.user.SignupDto;
import com.roman.bookshelf.domain.user.UserDto;
import com.roman.bookshelf.persistance.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    User signUpToUser(SignupDto signupDto);
    default String map(char[] value) {
        return value != null ? new String(value) : null;
    }
}
