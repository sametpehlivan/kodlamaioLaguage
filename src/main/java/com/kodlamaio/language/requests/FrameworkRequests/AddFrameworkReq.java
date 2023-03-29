package com.kodlamaio.language.requests.FrameworkRequests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddFrameworkReq extends FrameworkReq
{
    private int languageId;
    public AddFrameworkReq()
    {
        super();
    }
    public AddFrameworkReq(int languageId,String name)
    {
        super(name);
        this.languageId = languageId;
    }
}
