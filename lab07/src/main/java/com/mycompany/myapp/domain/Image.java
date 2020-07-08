package com.mycompany.myapp.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A Image.
 */
@Entity
@Table(name = "image")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "set_up_date")
    private ZonedDateTime setUpDate;

    @OneToMany(mappedBy = "image")
    private Set<Unit> units = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public Image image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Image imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public ZonedDateTime getSetUpDate() {
        return setUpDate;
    }

    public Image setUpDate(ZonedDateTime setUpDate) {
        this.setUpDate = setUpDate;
        return this;
    }

    public void setSetUpDate(ZonedDateTime setUpDate) {
        this.setUpDate = setUpDate;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public Image units(Set<Unit> units) {
        this.units = units;
        return this;
    }

    public Image addUnit(Unit unit) {
        this.units.add(unit);
        unit.setImage(this);
        return this;
    }

    public Image removeUnit(Unit unit) {
        this.units.remove(unit);
        unit.setImage(null);
        return this;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Image)) {
            return false;
        }
        return id != null && id.equals(((Image) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Image{" +
            "id=" + getId() +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", setUpDate='" + getSetUpDate() + "'" +
            "}";
    }
}
