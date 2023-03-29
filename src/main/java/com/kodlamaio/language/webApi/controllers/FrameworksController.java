package com.kodlamaio.language.webApi.controllers;

import com.kodlamaio.language.business.abstracts.FrameworkService;
import com.kodlamaio.language.requests.FrameworkRequests.AddFrameworkReq;
import com.kodlamaio.language.requests.FrameworkRequests.GetByIdFrameworkReq;
import com.kodlamaio.language.responses.FrameworkResponses.FrameworkRes;
import com.kodlamaio.language.responses.FrameworkResponses.GetByIdFrameworkRes;
import com.kodlamaio.language.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/frameworks")
@RestController
public class FrameworksController
{
    @Autowired
    private FrameworkService frameworkService;
    @GetMapping
    public Response<List<FrameworkRes>> getAll()
    {
        return frameworkService.getAll();
    }
    @GetMapping("/{id}")
    public Response<GetByIdFrameworkRes> getById(@PathVariable(value = "id") int id)
    {
        return frameworkService.getById(new GetByIdFrameworkReq(id));
    }
    @PostMapping
    public Response<FrameworkRes> add(@RequestBody AddFrameworkReq request)
    {
        return frameworkService.add(request);
    }
}
