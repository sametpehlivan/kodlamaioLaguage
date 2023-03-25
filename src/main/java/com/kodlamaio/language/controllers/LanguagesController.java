package com.kodlamaio.language.controllers;

import com.kodlamaio.language.business.abstracts.LanguageService;
import com.kodlamaio.language.entity.concretes.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/languages")
@RestController
public class LanguagesController
{
    private LanguageService languagesService;
    @Autowired
    public LanguagesController(LanguageService languagesService)
    {
        this.languagesService = languagesService;
    }
    @GetMapping
    public List<Language> getAll()
    {
        return languagesService.getAll();
    }
    @GetMapping("/{id}")
    public Language getById(@PathVariable(value = "id") int id){
        return languagesService.getById(id);
    }
    @DeleteMapping("/{id}")
    public Language delete(@PathVariable(value = "id") int id)
    {
        return languagesService.delete(id);
    }
    @PostMapping
    public Language add(@RequestBody Language language) {
        return languagesService.add(language);
    }
    @PutMapping
    public Language update(@RequestBody Language language)
    {
        return languagesService.update(language);
    }
}
