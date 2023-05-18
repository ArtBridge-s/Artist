package com.artbridge.artist.service.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link com.artbridge.artist.domain.Like} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class LikeDTO implements Serializable {

    private Long id;
    private MemberDTO memberDTO;
    private ArtistDTO artistDTO;
}
