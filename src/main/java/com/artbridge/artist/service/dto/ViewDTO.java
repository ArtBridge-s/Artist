package com.artbridge.artist.service.dto;

import com.artbridge.artist.domain.model.View;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link View} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class ViewDTO implements Serializable {

    private Long id;
    private MemberDTO memberDTO;
    private ArtistDTO artistDTO;

}
