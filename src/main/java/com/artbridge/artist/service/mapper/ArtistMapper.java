package com.artbridge.artist.service.mapper;

import com.artbridge.artist.domain.Artist;
import com.artbridge.artist.service.dto.ArtistDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Artist} and its DTO {@link ArtistDTO}.
 */
@Mapper(componentModel = "spring")
public interface ArtistMapper extends EntityMapper<ArtistDTO, Artist> {}
