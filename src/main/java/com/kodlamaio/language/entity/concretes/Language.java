package com.kodlamaio.language.entity.concretes;

import com.kodlamaio.language.entity.absratacts.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Language implements BaseEntity
{
    @NotEmpty(message = "Name not be null.")
    private String name;
    private int id;

    public Language() {}

    public Language(int id, String name)
    {
        setName(name);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public boolean equals(Object o)
    {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Language)) return false;
        Language other = (Language)o;
        boolean nameEquals = false;
        if (getName() != null && other.getName() != null) nameEquals = getName().equals(other.getName());
        if (getName() == null && other.getName() == null) nameEquals = true;
        return nameEquals;
    }

    @Override
    public int hashCode()
    {
        return  31 * 17 + (this.getName() != null ? this.getName().hashCode() : 0);
    }

}
