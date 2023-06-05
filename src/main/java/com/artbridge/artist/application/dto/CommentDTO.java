package com.artbridge.artist.application.dto;

import java.io.Serializable;

import com.artbridge.artist.domain.model.Comment;
import lombok.Data;

/**
 * A DTO for the {@link Comment} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class CommentDTO implements Serializable {

    private Long id;
    private MemberDTO memberDTO;
    private String content;
    private ArtistDTO artistDTO;
}
