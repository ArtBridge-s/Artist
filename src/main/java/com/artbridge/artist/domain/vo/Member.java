package com.artbridge.artist.domain.vo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Member implements Serializable {

    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_login")
    private String login;

    @Column(name = "member_name")
    private String name;
}
