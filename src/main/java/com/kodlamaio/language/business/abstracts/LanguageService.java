package com.kodlamaio.language.business.abstracts;

import com.kodlamaio.language.requests.LanguageRequests.DeleteLanguageReq;
import com.kodlamaio.language.requests.LanguageRequests.GetByIdLanguageReq;
import com.kodlamaio.language.requests.LanguageRequests.LanguageReq;
import com.kodlamaio.language.requests.LanguageRequests.UpdateLanguageReq;
import com.kodlamaio.language.responses.LanguageResponses.LanguageRes;
import com.kodlamaio.language.responses.LanguageResponses.LanguageWithFrameworkRes;
import com.kodlamaio.language.responses.Response;

import java.util.List;

public interface LanguageService
{
    Response<List<LanguageRes>> getAll();
    Response<LanguageRes> updateName(UpdateLanguageReq entity);
    Response<LanguageRes> remove(DeleteLanguageReq entity);
    Response<LanguageWithFrameworkRes> add(LanguageReq entity);
    Response<LanguageWithFrameworkRes> getById(GetByIdLanguageReq entity);
}
