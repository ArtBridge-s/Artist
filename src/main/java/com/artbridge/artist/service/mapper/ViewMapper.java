package com.artbridge.artist.service.mapper;

import com.artbridge.artist.domain.Artist;
import com.artbridge.artist.domain.View;
import com.artbridge.artist.service.dto.ArtistDTO;
import com.artbridge.artist.service.dto.ViewDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link View} and its DTO {@link ViewDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewMapper extends EntityMapper<ViewDTO, View> {
    @Mapping(target = "artwork", source = "artwork", qualifiedByName = "artistId")
    ViewDTO toDto(View s);

    @Named("artistId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ArtistDTO toDtoArtistId(Artist artist);
}
