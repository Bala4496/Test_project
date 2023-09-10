package com.example.test.mapper;

import com.example.test.dto.RegisterDTO;
import com.example.test.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisterMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target =  "updatedAt", ignore = true)
    User map(RegisterDTO registerDTO);

    @InheritInverseConfiguration
    RegisterDTO map(User user);
}
