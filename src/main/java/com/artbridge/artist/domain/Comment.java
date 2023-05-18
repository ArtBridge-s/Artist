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
 * A Comment.
 */
@Entity
@Table(name = "comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Embedded
    private Member member;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JsonIgnoreProperties(value = { "comments", "views", "likes" }, allowSetters = true)
    private Artist artist;

    public Comment id(Long id) {
        this.setId(id);
        return this;
    }

    public Comment voMember(Member member) {
        this.setMember(member);
        return this;
    }

    public Comment content(String content) {
        this.setContent(content);
        return this;
    }

    public Comment artwork(Artist artist) {
        this.setArtist(artist);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;
        return getId() != null && Objects.equals(getId(), comment.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
