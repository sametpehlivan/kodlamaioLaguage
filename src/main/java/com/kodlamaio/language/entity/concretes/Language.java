package com.kodlamaio.language.entity.concretes;

import com.kodlamaio.language.entity.absratacts.BaseEntity;
import lombok.*;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "programming_langs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Language implements BaseEntity, Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name",nullable = false,length = 20)
    private String name;
    @Column(name = "slug",nullable = false,length = 35)
    private String slug;
    @OneToMany(
            mappedBy = "language",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Framework> frameworks;
    public boolean equals(Object o)
    {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Language)) return false;
        Language other = (Language)o;
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
