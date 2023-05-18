package com.artbridge.artist.domain.valueobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class Artwork implements Serializable {

    @Column(name = "artwork_title")
    private String title;
}