package com.artbridge.artist.service.mapper;

import com.artbridge.artist.domain.Like;
import com.artbridge.artist.service.dto.LikeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Like} and its DTO {@link LikeDTO}.
 */
@Mapper(componentModel = "spring")
public interface LikeMapper extends EntityMapper<LikeDTO, Like> {
    @Mapping(target = "artistDTO", source = "artist")
    @Mapping(target = "memberDTO", source = "member")
    LikeDTO toDto(Like like);

    @Mapping(target = "artist", source = "artistDTO")
    @Mapping(target = "member", source = "memberDTO")
    Like toEntity(LikeDTO likeDTO);
}
