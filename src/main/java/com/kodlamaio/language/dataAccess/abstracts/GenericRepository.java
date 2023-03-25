package com.kodlamaio.language.dataAccess.abstracts;

import com.kodlamaio.language.entity.absratacts.BaseEntity;
import com.kodlamaio.language.entity.concretes.Language;

import java.util.List;
import java.util.function.Predicate;

public interface GenericRepository <T extends BaseEntity>
{
    List<T> getAll();
    T getById(int id);
    T delete(int id);
    List<T> getAllFilter(Predicate<? super T> filter);
    Language add(Language language);
    int getLastId();
    Language update(Language entity);
}
