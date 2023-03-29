package com.kodlamaio.language.requests.FrameworkRequests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFrameworkReq extends FrameworkReq
{
    private int id;
    public UpdateFrameworkReq()
    {
        super();
    }
    public UpdateFrameworkReq(int id,String name)
    {
        super(name);
        this.id = id;
    }
}
