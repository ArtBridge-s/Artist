package com.artbridge.artist.service.mapper;

import com.artbridge.artist.domain.model.View;
import com.artbridge.artist.service.dto.ViewDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link View} and its DTO {@link ViewDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewMapper extends EntityMapper<ViewDTO, View> {

    @Mapping(target = "artistDTO", source = "artist")
    @Mapping(target = "memberDTO", source = "member")
    ViewDTO toDto(View view);

    @Mapping(target = "artist", source = "artistDTO")
    @Mapping(target = "member", source = "memberDTO")
    View toEntity(ViewDTO viewDTO);
}
