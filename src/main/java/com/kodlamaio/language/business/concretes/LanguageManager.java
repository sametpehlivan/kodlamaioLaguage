package com.kodlamaio.language.business.concretes;

import com.kodlamaio.language.business.abstracts.LanguageService;
import com.kodlamaio.language.dataAccess.abstracts.LanguageRepository;
import com.kodlamaio.language.entity.concretes.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LanguageManager implements LanguageService
{
    private LanguageRepository repository;
    @Autowired
    public LanguageManager(LanguageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Language> getAll() {
        // todo business logic
        return repository.getAll();
    }

    @Override
    public Language getById(int id)
    {
        Language result = repository.getAllFilter(brand -> brand.getId() == id)
                        .size() > 0 ?  repository.getById(id) : null;
        return result;
    }

    @Override
    public Language delete(int id) {
        Language result = repository.getAllFilter(brand -> brand.getId() == id)
                        .size() > 0 ?  repository.delete(id) : null;
        return result;
    }

    @Override
    public Language add(Language entity) {
        if (entity.getId()<=0) return null;
        if (entity.getName().isEmpty()) return null;
        entity.setId(repository.getLastId()+1);
        Language result = repository.getAllFilter(brand -> brand.getName().equals(entity.getName()))
                        .size() > 0 ?  null : repository.add(entity);
        return result;
    }
    public Language update(Language entity)
    {
        if (entity.getId()<=0) return null;
        if (entity.getName().isEmpty()) return null;
        Language result = repository.getAllFilter(brand -> brand.getId() == entity.getId())
                        .size() > 0 ?  repository.update(entity) : null;
        return result;
    }

}
