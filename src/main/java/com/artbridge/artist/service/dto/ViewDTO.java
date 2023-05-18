package com.artbridge.artist.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.artbridge.artist.domain.View} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class ViewDTO implements Serializable {

    private Long id;
    private Long voMember;
    private ArtistDTO artwork;

}
