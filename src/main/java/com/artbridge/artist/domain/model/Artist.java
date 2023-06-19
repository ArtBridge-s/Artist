package com.artbridge.artist.domain.model;

import com.artbridge.artist.domain.standardType.Status;
import com.artbridge.artist.domain.vo.Artwork;
import com.artbridge.artist.domain.vo.Member;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Artist.
 */
@Entity
@Table(name = "artist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Artist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "info", length = 4000)
    private String info;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "phone")
    private String phone;

    @Column(name = "career")
    private String career;

    @Embedded
    private Member createdMember;

    @Embedded
    private Artwork artwork;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnoreProperties(value = { "artist" }, allowSetters = true)
    @ToString.Exclude
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnoreProperties(value = { "artist" }, allowSetters = true)
    @ToString.Exclude
    private Set<View> views = new HashSet<>();

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnoreProperties(value = { "artist" }, allowSetters = true)
    @ToString.Exclude
    private Set<Like> likes = new HashSet<>();

    public Artist id(Long id) {
        this.setId(id);
        return this;
    }

    public Artist name(String name) {
        this.setName(name);
        return this;
    }

    public Artist realName(String realName) {
        this.setRealName(realName);
        return this;
    }

    public Artist imgUrl(String imgUrl) {
        this.setImgUrl(imgUrl);
        return this;
    }

    public Artist phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public Artist career(String career) {
        this.setCareer(career);
        return this;
    }

    public Artist voArtwork(Artwork artwork) {
        this.setArtwork(artwork);
        return this;
    }

    public Artist member(Member member) {
        this.setCreatedMember(member);
        return this;
    }

    public Artist status(Status status) {
        this.setStatus(status);
        return this;
    }

    public void setComments(Set<Comment> comments) {
        if (this.comments != null) {
            this.comments.forEach(i -> i.setArtist(null));
        }
        if (comments != null) {
            comments.forEach(i -> i.setArtist(this));
        }
        this.comments = comments;
    }

    public Artist addComments(Comment comment) {
        this.comments.add(comment);
        comment.setArtist(this);
        return this;
    }

    public Artist removeComments(Comment comment) {
        this.comments.remove(comment);
        comment.setArtist(null);
        return this;
    }

    public void setViews(Set<View> views) {
        if (this.views != null) {
            this.views.forEach(i -> i.setArtist(null));
        }
        if (views != null) {
            views.forEach(i -> i.setArtist(this));
        }
        this.views = views;
    }

    public Artist views(Set<View> views) {
        this.setViews(views);
        return this;
    }

    public Artist addViews(View view) {
        this.views.add(view);
        view.setArtist(this);
        return this;
    }

    public Artist removeViews(View view) {
        this.views.remove(view);
        view.setArtist(null);
        return this;
    }

    public void setLikes(Set<Like> likes) {
        if (this.likes != null) {
            this.likes.forEach(i -> i.setArtist(null));
        }
        if (likes != null) {
            likes.forEach(i -> i.setArtist(this));
        }
        this.likes = likes;
    }

    public Artist likes(Set<Like> likes) {
        this.setLikes(likes);
        return this;
    }

    public Artist addLikes(Like like) {
        this.likes.add(like);
        like.setArtist(this);
        return this;
    }

    public Artist removeLikes(Like like) {
        this.likes.remove(like);
        like.setArtist(null);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Artist artist = (Artist) o;
        return getId() != null && Objects.equals(getId(), artist.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
