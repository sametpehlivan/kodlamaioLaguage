package com.kodlamaio.language.dataAccess.concretes.InMemory;

import com.kodlamaio.language.dataAccess.abstracts.LanguageRepository;
import com.kodlamaio.language.entity.concretes.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class InMemoryLanguageRepository implements LanguageRepository
{
    private List<Language> languages;
    @Autowired
    public InMemoryLanguageRepository()
    {
        languages = new ArrayList<>();
        languages.add(new Language(1,"c"));
        languages.add(new Language(2,"c++"));
        languages.add(new Language(3,"c#"));
        languages.add(new Language(4,"python"));
    }

    @Override
    public List<Language> getAll()
    {
        return languages;
    }

    @Override
    public Language getById(int id)
    {
        return getAllFilter(b-> b.getId() == id).get(0);
    }

    @Override
    public Language delete(int id) {
        Language result = getById(id);
        languages.remove(getById(id));
        return result;
    }

    @Override
    public List<Language> getAllFilter(Predicate<? super Language> filter) {
        return languages.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public Language add(Language language)
    {
        languages.add(language);
        return language;
    }
    @Override
    public Language update(Language entity)
    {
       languages.forEach(b-> {
           if (b.getId() == entity.getId())
           {
               b.setName(entity.getName());
           }
       });
       return entity;
    }
    public int getLastId(){
        Collections.sort(languages,(b1, b2) -> {
            if (b1.getId() < b2.getId()) return -1;
            if (b1.getId() > b2.getId()) return 1;
            return 0;
        });
        return languages.get(languages.size()-1).getId();
    }


}
