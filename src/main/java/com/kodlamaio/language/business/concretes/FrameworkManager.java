package com.kodlamaio.language.business.concretes;

import com.kodlamaio.language.Utils.Helpers;
import com.kodlamaio.language.business.abstracts.FrameworkService;
import com.kodlamaio.language.dataAccess.abstracts.FrameworkRepository;
import com.kodlamaio.language.dataAccess.abstracts.LanguageRepository;
import com.kodlamaio.language.entity.concretes.Framework;
import com.kodlamaio.language.requests.FrameworkRequests.AddFrameworkReq;
import com.kodlamaio.language.requests.FrameworkRequests.DeleteFrameworkReq;
import com.kodlamaio.language.requests.FrameworkRequests.GetByIdFrameworkReq;
import com.kodlamaio.language.requests.FrameworkRequests.UpdateFrameworkReq;
import com.kodlamaio.language.responses.FrameworkResponses.FrameworkRes;
import com.kodlamaio.language.responses.FrameworkResponses.GetByIdFrameworkRes;
import com.kodlamaio.language.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FrameworkManager implements FrameworkService {

    @Autowired
    private FrameworkRepository repository;
    @Autowired
    private LanguageRepository languageRepository;
    @Override
    public Response<List<FrameworkRes>> getAll() {
        Response<List<FrameworkRes>> response = new Response<>();
        try {
            response.setData(new ArrayList<>());
            response.setStatus(HttpStatus.OK);
            response.setMessage("");
            repository.findAll().forEach(f-> response.getData().add(new FrameworkRes(f.getName())));
        }
        catch (Exception e)
        {
            response.setData(null);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response<FrameworkRes> updateName(UpdateFrameworkReq entity)
    {
        Response<FrameworkRes> response = new Response<>();
        try
        {
            if (entity.getName() == null || entity.getName().isEmpty()) throw  new Exception("Name cannot be empty");
            if (!repository.existsById(entity.getId())) throw new Exception("Not Found Framework");
            if (!repository.existsBySlug(entity.getName())) throw new Exception("Already Added");

            Framework framework = new Framework();
            framework.setName(entity.getName());
            framework.setSlug(Helpers.slug(entity.getName()));
            framework.setId(entity.getId());

            repository.updateName(framework.getId(), framework.getName(), framework.getSlug());

            response.setStatus(HttpStatus.OK);
            response.setMessage("Updated Successfully");
            response.setData(new FrameworkRes(framework.getName()));
        }
        catch (Exception e)
        {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response<FrameworkRes> remove(DeleteFrameworkReq entity) {
        Response<FrameworkRes> response = new Response<>();
        try
        {
            if(!repository.existsById(entity.getId())) throw new Exception("Not found framework");
            Framework result = repository.findById(entity.getId());
            repository.deleteById(entity.getId());

            response.setStatus(HttpStatus.OK);
            response.setMessage("Deleted Successfully");
            response.setData(new FrameworkRes(result.getName()));

        }catch (Exception e)
        {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response<FrameworkRes> add(AddFrameworkReq entity)
    {
        var response = new Response<FrameworkRes>();
        try
        {
            //todo aop
            if (entity.getName() == null || entity.getName().isEmpty()) throw  new Exception("Name cannot be empty");
            if (repository.existsBySlug(Helpers.slug(entity.getName()))) throw  new Exception("Framework Already Added");
            if (!languageRepository.existsById(entity.getLanguageId())) throw new Exception("Not Found Language");

            Framework framework = new Framework();
            framework.setName(entity.getName());
            framework.setSlug(Helpers.slug(entity.getName()));
            framework.setLanguage(languageRepository.findById(entity.getLanguageId()));
            var result = repository.save(framework);

            response.setStatus(HttpStatus.OK);
            response.setMessage("Added Successfully");
            response.setData(new FrameworkRes(result.getName()));

        }catch (Exception e)
        {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public Response<GetByIdFrameworkRes> getById(GetByIdFrameworkReq entity) {
        Response<GetByIdFrameworkRes> response = new Response<>();
        try
        {
            if(!repository.existsById(entity.getId())) throw new Exception("Not found framework");
            var result = repository.findById(entity.getId());
            response.setStatus(HttpStatus.OK);
            response.setMessage("Query Successfully");
            response.setData(new GetByIdFrameworkRes(result.getName(), result.getLanguage().getName()));
        }catch (Exception e)
        {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            response.setData(null);
        }
        return response;
    }

}
