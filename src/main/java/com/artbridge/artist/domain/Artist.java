package com.artbridge.artist.domain;

import com.artbridge.artist.domain.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Artist.
 */
@Entity
@Table(name = "artist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Artist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "phone")
    private String phone;

    @Column(name = "career")
    private String career;

    @Column(name = "vo_artwork")
    private String voArtwork;

    @Column(name = "vo_member")
    private String voMember;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "artwork")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "artwork" }, allowSetters = true)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "artwork")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "artwork" }, allowSetters = true)
    private Set<View> views = new HashSet<>();

    @OneToMany(mappedBy = "artwork")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "artwork" }, allowSetters = true)
    private Set<Like> likes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Artist id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Artist name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return this.realName;
    }

    public Artist realName(String realName) {
        this.setRealName(realName);
        return this;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public Artist imgUrl(String imgUrl) {
        this.setImgUrl(imgUrl);
        return this;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPhone() {
        return this.phone;
    }

    public Artist phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCareer() {
        return this.career;
    }

    public Artist career(String career) {
        this.setCareer(career);
        return this;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getVoArtwork() {
        return this.voArtwork;
    }

    public Artist voArtwork(String voArtwork) {
        this.setVoArtwork(voArtwork);
        return this;
    }

    public void setVoArtwork(String voArtwork) {
        this.voArtwork = voArtwork;
    }

    public String getVoMember() {
        return this.voMember;
    }

    public Artist voMember(String voMember) {
        this.setVoMember(voMember);
        return this;
    }

    public void setVoMember(String voMember) {
        this.voMember = voMember;
    }

    public Status getStatus() {
        return this.status;
    }

    public Artist status(Status status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        if (this.comments != null) {
            this.comments.forEach(i -> i.setArtwork(null));
        }
        if (comments != null) {
            comments.forEach(i -> i.setArtwork(this));
        }
        this.comments = comments;
    }

    public Artist comments(Set<Comment> comments) {
        this.setComments(comments);
        return this;
    }

    public Artist addComments(Comment comment) {
        this.comments.add(comment);
        comment.setArtwork(this);
        return this;
    }

    public Artist removeComments(Comment comment) {
        this.comments.remove(comment);
        comment.setArtwork(null);
        return this;
    }

    public Set<View> getViews() {
        return this.views;
    }

    public void setViews(Set<View> views) {
        if (this.views != null) {
            this.views.forEach(i -> i.setArtwork(null));
        }
        if (views != null) {
            views.forEach(i -> i.setArtwork(this));
        }
        this.views = views;
    }

    public Artist views(Set<View> views) {
        this.setViews(views);
        return this;
    }

    public Artist addViews(View view) {
        this.views.add(view);
        view.setArtwork(this);
        return this;
    }

    public Artist removeViews(View view) {
        this.views.remove(view);
        view.setArtwork(null);
        return this;
    }

    public Set<Like> getLikes() {
        return this.likes;
    }

    public void setLikes(Set<Like> likes) {
        if (this.likes != null) {
            this.likes.forEach(i -> i.setArtwork(null));
        }
        if (likes != null) {
            likes.forEach(i -> i.setArtwork(this));
        }
        this.likes = likes;
    }

    public Artist likes(Set<Like> likes) {
        this.setLikes(likes);
        return this;
    }

    public Artist addLikes(Like like) {
        this.likes.add(like);
        like.setArtwork(this);
        return this;
    }

    public Artist removeLikes(Like like) {
        this.likes.remove(like);
        like.setArtwork(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Artist)) {
            return false;
        }
        return id != null && id.equals(((Artist) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Artist{" +
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
