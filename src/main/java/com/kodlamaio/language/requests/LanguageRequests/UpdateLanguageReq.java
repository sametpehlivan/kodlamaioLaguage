package com.kodlamaio.language.requests.LanguageRequests;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UpdateLanguageReq extends LanguageReq implements Serializable
{
    private int id;
    public UpdateLanguageReq()
    {
        super();
    }
    public UpdateLanguageReq (int id, String name , List<FrameworkReq> frameworks)
    {
        super(name,frameworks);
        this.id = id;
    }
}
