package com.artbridge.artist.service.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link com.artbridge.artist.domain.Comment} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class CommentDTO implements Serializable {

    private Long id;
    private MemberDTO memberDTO;
    private String content;
    private ArtistDTO artistDTO;
}
