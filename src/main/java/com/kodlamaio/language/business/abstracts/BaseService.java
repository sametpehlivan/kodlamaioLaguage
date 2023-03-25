package com.kodlamaio.language.business.abstracts;

import com.kodlamaio.language.entity.absratacts.BaseEntity;

import java.util.List;

public interface BaseService <T extends BaseEntity>
{
    List<T> getAll();
    T getById(int id);
    T delete(int id);
    T add(T entity);
    T update(T entity);
}
