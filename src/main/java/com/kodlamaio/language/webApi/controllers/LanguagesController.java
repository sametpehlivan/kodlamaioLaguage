package com.kodlamaio.language.webApi.controllers;

import com.kodlamaio.language.business.abstracts.LanguageService;
import com.kodlamaio.language.requests.LanguageRequests.DeleteLanguageReq;
import com.kodlamaio.language.requests.LanguageRequests.GetByIdLanguageReq;
import com.kodlamaio.language.requests.LanguageRequests.LanguageReq;
import com.kodlamaio.language.requests.LanguageRequests.UpdateLanguageReq;
import com.kodlamaio.language.responses.LanguageResponses.LanguageRes;
import com.kodlamaio.language.responses.LanguageResponses.LanguageWithFrameworkRes;
import com.kodlamaio.language.responses.Response;
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
    public Response<List<LanguageRes>> getAll()
    {
        return languagesService.getAll();
    }
    @GetMapping("/{id}")
    public Response<LanguageWithFrameworkRes> getById(@PathVariable(value = "id") int id)
    {
        return languagesService.getById(new GetByIdLanguageReq(id));
    }
    @PostMapping
    public Response<LanguageWithFrameworkRes> addLanguage(@RequestBody LanguageReq request)
    {
        return languagesService.add(request);
    }
    @PutMapping
    public Response<LanguageRes> updateLanguageName(@RequestBody UpdateLanguageReq request)
    {
        return languagesService.updateName(request);
    }
    @DeleteMapping
    public Response<LanguageRes> remove(@RequestBody DeleteLanguageReq request)
    {
        return languagesService.remove(request);
    }

}
