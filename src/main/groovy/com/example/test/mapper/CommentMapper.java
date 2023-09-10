package com.example.test.mapper;

import com.example.test.dto.CommentDTO;
import com.example.test.model.Comment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment map(CommentDTO comment);

    @InheritInverseConfiguration
    CommentDTO map(Comment comment);
}
