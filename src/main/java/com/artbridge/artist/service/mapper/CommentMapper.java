package com.artbridge.artist.service.mapper;

import com.artbridge.artist.domain.Artist;
import com.artbridge.artist.domain.Comment;
import com.artbridge.artist.service.dto.ArtistDTO;
import com.artbridge.artist.service.dto.CommentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDTO}.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {
    @Mapping(target = "artwork", source = "artwork", qualifiedByName = "artistId")
    CommentDTO toDto(Comment s);

    @Named("artistId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ArtistDTO toDtoArtistId(Artist artist);
}
