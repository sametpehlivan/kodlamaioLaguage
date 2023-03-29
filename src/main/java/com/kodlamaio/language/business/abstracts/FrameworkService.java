package com.kodlamaio.language.business.abstracts;

import com.kodlamaio.language.requests.FrameworkRequests.AddFrameworkReq;
import com.kodlamaio.language.requests.FrameworkRequests.DeleteFrameworkReq;
import com.kodlamaio.language.requests.FrameworkRequests.GetByIdFrameworkReq;
import com.kodlamaio.language.requests.FrameworkRequests.UpdateFrameworkReq;
import com.kodlamaio.language.responses.FrameworkResponses.FrameworkRes;
import com.kodlamaio.language.responses.FrameworkResponses.GetByIdFrameworkRes;
import com.kodlamaio.language.responses.Response;

import java.util.List;

public interface FrameworkService
{
    Response<List<FrameworkRes>> getAll();
    Response<FrameworkRes> updateName(UpdateFrameworkReq entity);
    Response<FrameworkRes> remove(DeleteFrameworkReq entity);
    Response<FrameworkRes> add(AddFrameworkReq entity);
    Response<GetByIdFrameworkRes> getById(GetByIdFrameworkReq entity);
}
