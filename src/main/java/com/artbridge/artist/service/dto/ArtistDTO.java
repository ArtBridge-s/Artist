package com.artbridge.artist.service.dto;

import com.artbridge.artist.domain.enumeration.Status;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.artbridge.artist.domain.Artist} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ArtistDTO implements Serializable {

    private Long id;

    private String name;

    private String realName;

    private String imgUrl;

    private String phone;

    private String career;

    private String voArtwork;

    private String voMember;

    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getVoArtwork() {
        return voArtwork;
    }

    public void setVoArtwork(String voArtwork) {
        this.voArtwork = voArtwork;
    }

    public String getVoMember() {
        return voMember;
    }

    public void setVoMember(String voMember) {
        this.voMember = voMember;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArtistDTO)) {
            return false;
        }

        ArtistDTO artistDTO = (ArtistDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, artistDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArtistDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", realName='" + getRealName() + "'" +
            ", imgUrl='" + getImgUrl() + "'" +
            ", phone='" + getPhone() + "'" +
            ", career='" + getCareer() + "'" +
            ", voArtwork='" + getVoArtwork() + "'" +
            ", voMember='" + getVoMember() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
