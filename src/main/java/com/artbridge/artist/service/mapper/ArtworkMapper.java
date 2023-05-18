package com.artbridge.artist.service.mapper;

import com.artbridge.artist.domain.valueobject.Artwork;
import com.artbridge.artist.service.dto.ArtworkDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtworkMapper extends EntityMapper<ArtworkDTO, Artwork> {}
