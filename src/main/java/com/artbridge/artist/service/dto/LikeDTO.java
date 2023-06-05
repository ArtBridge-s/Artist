package com.artbridge.artist.service.dto;

import java.io.Serializable;

import com.artbridge.artist.domain.model.Like;
import lombok.Data;

/**
 * A DTO for the {@link Like} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class LikeDTO implements Serializable {

    private Long id;
    private MemberDTO memberDTO;
    private ArtistDTO artistDTO;
}
