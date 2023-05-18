package com.artbridge.artist.domain;

import com.artbridge.artist.domain.valueobject.Member;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Like.
 */
@Entity
@Table(name = "jhi_like")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Like implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Embedded
    private Member member;

    @ManyToOne
    @JsonIgnoreProperties(value = { "comments", "views", "likes" }, allowSetters = true)
    private Artist artist;

    public Like id(Long id) {
        this.setId(id);
        return this;
    }

    public Like member(Member member) {
        this.setMember(member);
        return this;
    }

    public Like artwork(Artist artist) {
        this.setArtist(artist);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Like like = (Like) o;
        return getId() != null && Objects.equals(getId(), like.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
