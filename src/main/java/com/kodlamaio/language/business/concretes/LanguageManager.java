package com.kodlamaio.language.business.concretes;

import com.kodlamaio.language.Utils.Helpers;
import com.kodlamaio.language.business.abstracts.LanguageService;
import com.kodlamaio.language.dataAccess.abstracts.FrameworkRepository;
import com.kodlamaio.language.dataAccess.abstracts.LanguageRepository;
import com.kodlamaio.language.entity.concretes.Framework;
import com.kodlamaio.language.entity.concretes.Language;
import com.kodlamaio.language.requests.LanguageRequests.*;
import com.kodlamaio.language.responses.LanguageResponses.*;
import com.kodlamaio.language.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageManager implements LanguageService
{
    @Autowired
    private LanguageRepository repository;
    @Autowired
    private FrameworkRepository frameworkRepository;
    @Override
    public Response<List<LanguageRes>> getAll()
    {
        Response<List<LanguageRes>> response = new Response<>();
        try {
            response.setData(new ArrayList<>());
            response.setStatus(HttpStatus.OK);
            response.setMessage("");
            repository.findAll().forEach( l -> {
                var language = new LanguageRes();
                language.setName(l.getName());
                response.getData().add(language);
            });
        }catch (Exception e)
        {
            response.setData(null);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage("");
        }
        return response;
    }

    @Override
    @Transactional
    public Response<LanguageWithFrameworkRes> add(LanguageReq entity) {

        var response = new Response<LanguageWithFrameworkRes>();
        try {
            if (entity.getName() == null || entity.getName().isEmpty()) throw  new Exception("Name cannot be empty");
            if (repository.existsBySlug(Helpers.slug(entity.getName()))) throw new Exception("Already Used");

            Language language = new Language();
            List<Framework> frameworks = new ArrayList<>();

            if (entity.getFrameworks() != null)
            {
                for (FrameworkReq f: entity.getFrameworks()) {

                    if (f.getName() == null || f.getName().isEmpty()) continue;
                    Framework framework = new Framework();
                    framework.setName(f.getName());
                    framework.setSlug(Helpers.slug(framework.getName()));
                    framework.setLanguage(language);
                    if (!frameworks.contains(framework) && !frameworkRepository.existsBySlug(framework.getSlug()))
                        frameworks.add(framework);

                }
            }
            language.setFrameworks(frameworks);
            language.setName(entity.getName());
            language.setSlug(Helpers.slug(entity.getName()));

            var result = repository.save(language);
            response.setStatus(HttpStatus.OK);
            response.setMessage("Added Successfully");
            response.setData(new LanguageWithFrameworkRes());

            response.getData().setName(result.getName());
            response.getData().setFrameworks(new ArrayList<>());

            result.getFrameworks()
                    .forEach( f -> response.getData().getFrameworks().add(new FrameworkRes(f.getName())));
        }catch (Exception e)
        {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            response.setData(null);
        }
        return response;
    }
    @Transactional
    @Override
    public Response<LanguageRes> updateName(UpdateLanguageReq entity)
    {
        Response<LanguageRes> response = new Response<>();
        try {
            if (entity.getName() == null || entity.getName().isEmpty()) throw  new Exception("Name cannot be empty");
            if (!repository.existsById(entity.getId()))  throw new Exception("Not Found");
            if (repository.existsBySlug(Helpers.slug(entity.getName()))) throw new Exception("Already Used");
            Language language = new Language();
            language.setId(entity.getId());
            language.setName(entity.getName());
            language.setSlug(Helpers.slug(entity.getName()));

            repository.updateName(language.getId(), language.getName(), language.getSlug());
            response.setData(new LanguageRes(language.getName()));
            response.setStatus(HttpStatus.OK);
            response.setMessage("");

        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response<LanguageRes> remove(DeleteLanguageReq entity)
    {
        Response<LanguageRes> response = new Response<>();
        try {
            if (!repository.existsById(entity.getId())) throw new Exception("Not Found");
            Language result =  repository.findById(entity.getId());
            repository.deleteById(entity.getId());

            response.setStatus(HttpStatus.OK);
            response.setMessage("");
            response.setData(new LanguageRes(result.getName()));

        }
        catch (Exception e){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response<LanguageWithFrameworkRes> getById(GetByIdLanguageReq entity) {
        Response<LanguageWithFrameworkRes> response = new Response<>();
        try
        {
            if (!repository.existsById(entity.getId())) throw new Exception("Not Found");
            var result = repository.findById(entity.getId());

            response.setStatus(HttpStatus.OK);
            response.setData(new LanguageWithFrameworkRes());
            response.setMessage("Query Successfully");

            response.getData().setName(result.getName());
            response.getData().setFrameworks(new ArrayList<>());
            result.getFrameworks().forEach(f -> response.getData().getFrameworks().add(new FrameworkRes(f.getName())));

        }catch (Exception e)
        {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            response.setData(null);
        }
        return response;
    }


}
