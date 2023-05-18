package com.artbridge.artist.service.dto;

import com.artbridge.artist.domain.enumeration.Status;
import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link com.artbridge.artist.domain.Artist} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class ArtistDTO implements Serializable {

    private Long id;
    private String name;
    private String realName;
    private String imgUrl;
    private String phone;
    private String career;
    private ArtworkDTO artworkDTO;
    private MemberDTO memberDTO;
    private Status status;
}
