package com.artbridge.artist.domain.valueobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class Member implements Serializable {

    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

}
