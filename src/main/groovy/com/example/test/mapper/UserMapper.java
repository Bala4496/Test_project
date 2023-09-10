package com.example.test.mapper;

import com.example.test.dto.UserDTO;
import com.example.test.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User map(UserDTO comment);

    @InheritInverseConfiguration
    UserDTO map(User comment);
}
