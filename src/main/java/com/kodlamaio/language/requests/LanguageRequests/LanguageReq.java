package com.kodlamaio.language.requests.LanguageRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageReq implements Serializable
{
    protected String name;
    protected List<FrameworkReq> frameworks;

}
