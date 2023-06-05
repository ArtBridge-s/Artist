package com.artbridge.artist.application.mapper;

import com.artbridge.artist.application.dto.CommentDTO;
import com.artbridge.artist.domain.model.Comment;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDTO}.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {
    @Mapping(target = "artistDTO", source = "artist")
    @Mapping(target = "memberDTO", source = "member")
    CommentDTO toDto(Comment comment);

    @Mapping(target = "artist", source = "artistDTO")
    @Mapping(target = "member", source = "memberDTO")
    Comment toEntity(CommentDTO commentDTO);
}
