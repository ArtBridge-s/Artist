package com.artbridge.artist.service.mapper;

import com.artbridge.artist.domain.Artist;
import com.artbridge.artist.domain.Like;
import com.artbridge.artist.service.dto.ArtistDTO;
import com.artbridge.artist.service.dto.LikeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Like} and its DTO {@link LikeDTO}.
 */
@Mapper(componentModel = "spring")
public interface LikeMapper extends EntityMapper<LikeDTO, Like> {
    @Mapping(target = "artwork", source = "artwork", qualifiedByName = "artistId")
    LikeDTO toDto(Like s);

    @Named("artistId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ArtistDTO toDtoArtistId(Artist artist);
}
