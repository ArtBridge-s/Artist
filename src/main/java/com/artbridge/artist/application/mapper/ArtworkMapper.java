package com.artbridge.artist.application.mapper;

import com.artbridge.artist.application.dto.ArtworkDTO;
import com.artbridge.artist.domain.vo.Artwork;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtworkMapper extends EntityMapper<ArtworkDTO, Artwork> {}
