package com.artbridge.artist.domain;

import com.artbridge.artist.domain.valueobject.Member;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A View.
 */
@Entity
@Table(name = "view")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class View implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "vo_member")
    private Member member;

    @ManyToOne
    @JsonIgnoreProperties(value = { "comments", "views", "likes" }, allowSetters = true)
    private Artist artist;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public View id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return this.member;
    }

    public View voMember(Member member) {
        this.setMember(member);
        return this;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Artist getArtwork() {
        return this.artist;
    }

    public void setArtwork(Artist artist) {
        this.artist = artist;
    }

    public View artwork(Artist artist) {
        this.setArtwork(artist);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof View)) {
            return false;
        }
        return id != null && id.equals(((View) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "View{" +
            "id=" + getId() +
            ", voMember=" + getMember() +
            "}";
    }
}
