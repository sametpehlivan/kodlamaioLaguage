package com.kodlamaio.language.entity.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "frameworks")
public class Framework
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name",nullable = false,length = 20)
    private String name;
    @Column(name = "slug",nullable = false,unique = true,length = 35)
    private String slug;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(
            name = "language_id",
            referencedColumnName = "id"
    )
    private Language language;
    public boolean equals(Object o)
    {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Framework)) return false;
        Framework other = (Framework)o;
        boolean nameEquals = false;
        if (getSlug() != null && other.getSlug() != null) nameEquals = getSlug().equals(other.getSlug());
        if (getSlug() == null && other.getSlug() == null) nameEquals = true;
        return nameEquals;
    }

    @Override
    public int hashCode()
    {
        return  31 * 17 + (this.getSlug() != null ? this.getSlug().hashCode() : 0);
    }
}
